package com.example.skvortsov.homework1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
//import android.support.v7.widget.DividerItemDecoration;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.skvortsov.homework1.DAO.DaoTaskDelete;
import com.example.skvortsov.homework1.DAO.DaoTaskInsert;
import com.example.skvortsov.homework1.DAO.DaoTaskLoadAll;
import com.example.skvortsov.homework1.Model.Event;
import com.example.skvortsov.homework1.jobs.ScheduleActivity;
import com.example.skvortsov.homework1.swipe.RecyclerItemTouchHelperCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

//new
public class ListFragment extends Fragment implements OnItemClickListener, RecyclerItemTouchHelperCallback.RecyclerItemTouchHelperListener {

    private RecyclerView  recyclerView;
    private EventAdapter recyclerAdapter;
    private final List<Event> eventList = new ArrayList<>();
    private FloatingActionButton floatingActionButton;
    ProgressDialog dialog ;
    ListFragment listFragment;
    static int numberOfItems=0;
    private SwipeRefreshLayout swipeRefreshLayout;
    private  View container;

    //new
    public void onItemClick(Event event) {
        /*
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);
        intent.putExtra(EventInfoActivity.ITEM_BUNDLE_NAME, event.getEventName());
        startActivity(intent);
        */
        Intent intent = new Intent(getActivity(), ScheduleActivity.class);
        startActivity(intent);
    }

    public ListFragment() {
        listFragment = this;
    }

    // as other
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("TEST", "TEST1");
        View view = null;
        try {
            view = inflater.inflate(R.layout.f_list, container, false);
        } catch (Exception ex) {
            Log.e("TEST", "TEST");
        } finally {

            return view;
        }
    }
/*
    public  void  refresh()
    {
        showProgressDialog("FireBase Loading. Please wait...");
        App.getInsance().getFirebaseCollection().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Event> events = new ArrayList<>();
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    Event event = documentSnapshot.toObject(Event.class);
                    event.setRemoteId(documentSnapshot.getReference().getId());
                    events.add(event);
                }

                dialog.dismiss();

                DaoTaskInsert daoTaskInsert = new DaoTaskInsert(new DaoTaskInsert.OnInsertDoneListener(){

                    @Override
                    public void onEndInsert() {
                        // do nothing
                        dialog.dismiss();
                        final DaoTaskLoadAll daoTaskLoadAll = new DaoTaskLoadAll(new DaoTaskLoadAll.OnLoadDoneListener(){
                            @Override
                            public void onEndLoad(List<Event> events) {
                                numberOfItems = events.size();
                                recyclerAdapter.setEvents(events);
                                dialog.dismiss();

                                // спрятать swipe
                                swipeRefreshLayout.setRefreshing(false);
                            }

                            @Override
                            public void onStartLoad() {
                                showProgressDialog("SQL Loading. Please wait...");
                            }
                        });

                        daoTaskLoadAll.execute();



                    }

                    @Override
                    public void onStartInsert() {
                        showProgressDialog("Inserting. Please wait...");
                    }

                }, events);

                daoTaskInsert.execute();


            }
        });
    }
*/

    public  void  refresh()
    {
        showProgressDialog("FireBase Loading. Please wait...");
        App.getInsance().getFirebaseCollection().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Event> events = new ArrayList<>();
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    Event event = documentSnapshot.toObject(Event.class);
                    event.setRemoteId(documentSnapshot.getReference().getId());
                    events.add(event);
                }

                dialog.dismiss();

                final DaoTaskLoadAll daoTaskLoadAll = new DaoTaskLoadAll(new DaoTaskLoadAll.OnLoadDoneListener(){
                    @Override
                    public void onEndLoad(List<Event> events) {
                        numberOfItems = events.size();
                        recyclerAdapter.setEvents(events);
                        dialog.dismiss();

                        // спрятать swipe
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onStartLoad() {
                        showProgressDialog("SQL Loading. Please wait...");
                    }
                });

                daoTaskLoadAll.execute();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_events);
        container = view.findViewById(R.id.container);
        dialog= new ProgressDialog(this.getContext()); // this = YourActivity

        //Button button = view.findViewById(R.id.change_list_button);

        floatingActionButton  = view.findViewById(R.id.add_event_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddEventActivity.class));
            }
        });

        for (int i = 0; i < 100; i++)
            eventList.add((new Event("Test " + i, new Date(), new Date(), "Body " + i)));
            //eventList.add((new Event("Test " + i, "Body " + i)));

        recyclerAdapter = new EventAdapter(eventList, this);

       // recyclerView.setAdapter(new EventAdapter(eventList, this));
        recyclerView.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelperCallback( this, 0,ItemTouchHelper.LEFT);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
     //   DividerItemDecoration decoration = new DividerItemDecoration(getContext(),linearLayoutManager.getOrientation());
      //  recyclerView.addItemDecoration(decoration);
/*
        // change list here
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Event> newEvents = new ArrayList<>();

                if (( MainActivity.getChangeStep() & 1) == 0) {

                    for (int i = 99; i >= 0; i--)
                        newEvents.add((new Event("Test " + i, new Date(), new Date(), "Body " + i)));
                        //newEvents.add((new Event("Test " + i,  "Body " + i)));
                }
                else
                    for (int i = 0; i < 100; i++)
                        newEvents.add((new Event("Test " + i, new Date(), new Date(), "Body " + i)));
                       // newEvents.add((new Event("Test " + i, "Body " + i)));

                MainActivity.setNewStep();

               eventAdapter.setEvents(newEvents);
            }
        });
        */


    }

    private void showProgressDialog(String s)
    {
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(s);
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        /*
                // кидаем скопом в FireBase
                WriteBatch writeBatch = App.getInsance().getFirebaseStore().batch();

                for(Event event:events){
                    DocumentReference documentReference = App.getInsance().getFirebaseCollection().document();
                    writeBatch.set(documentReference,event);
                }
                writeBatch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Batch success", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getContext(), "Batch failed", Toast.LENGTH_LONG).show();
                                                e.printStackTrace();
                                            }
                                        }
                );

*/
        refresh();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // for fragments
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof EventAdapter.EventViewHolder) {
            // get the removed item name to display it in snack bar
            String name = recyclerAdapter.getContactFromPosition(viewHolder.getAdapterPosition()).getEventName();
            // backup of removed item for undo purpose
            final Event deletedItem = recyclerAdapter.getContactFromPosition(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            // remove the item from recycler view
            recyclerAdapter.removeItem(viewHolder.getAdapterPosition());
            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    //.make(container, name + " removed from cart!", Snackbar.LENGTH_LONG);
                    .make(container, name + " removed from cart!", 5000);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // undo is selected, restore the deleted item
                    recyclerAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });

            snackbar.addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int sn_event) {
                    if (sn_event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                        // Snackbar closed on its own

                        final DaoTaskDelete daoTaskDelete = new DaoTaskDelete(new DaoTaskDelete.OnDeleteDoneListener() {
                            @Override
                            public void onEndDelete() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onStartDelete() {
                                showProgressDialog("SQL Deleting. Please wait...");
                            }
                        }, deletedItem);

                        daoTaskDelete.execute();
                    }
                }

                @Override
                public void onShown(Snackbar snackbar) {
                }
            });

            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    //
}

/*
if (viewHolder instanceof ContactViewHolder) {

            Contact contact = contactsAdapter.getContactFromPosition(viewHolder.getAdapterPosition());
            String name = contact.getFirstName();
            undoSnackbar = Snackbar
                    .make(container, name + " removed from list!", Snackbar.LENGTH_INDEFINITE);
            final Contact deletedItem = contactsAdapter.getContactFromPosition(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            contactsAdapter.removeItem(viewHolder.getAdapterPosition());

            undoSnackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contactsAdapter.restoreItem(deletedItem, deletedIndex);
                    undoHandler.removeCallbacks(undoRunnable);
                    undoSnackbar.dismiss();
                }
            });
            initUndoRunnable(contact);
            undoHandler.postDelayed(undoRunnable, UNDO_DELAY);
            undoSnackbar.setActionTextColor(Color.YELLOW);
            undoSnackbar.show();
        }        */