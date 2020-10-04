package com.example.rentride;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link trips#newInstance} factory method to
 * create an instance of this fragment.
 */
public class trips extends Fragment {
    private static View tripsView;
    private RecyclerView tripsList;
    private DatabaseReference tripsReference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public trips() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment trips.
     */
    // TODO: Rename and change types and number of parameters
    public static trips newInstance(String param1, String param2) {
        trips fragment = new trips();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tripsView = inflater.inflate(R.layout.fragment_trips, container, false);
        tripsList = (RecyclerView) tripsView.findViewById(R.id.recycler_view);
        tripsList.setLayoutManager(new LinearLayoutManager(getContext()));
        tripsList.setNestedScrollingEnabled(true);
        tripsReference = FirebaseDatabase.getInstance().getReference().child("PastTrip");
        return tripsView;
    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerOptions<PastTrips> options = new FirebaseRecyclerOptions.Builder<PastTrips>().setQuery(tripsReference, PastTrips.class).build();
        FirebaseRecyclerAdapter<PastTrips, ViewHolder> adapter = new FirebaseRecyclerAdapter<PastTrips, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, final int position, @NonNull PastTrips model) {

                holder.driverName.setText(model.getDriverName());
                holder.phone.setText(model.getPhone());
                holder.dateTime.setText(model.getDateTime());
                holder.pickupDis.setText(model.getPickupDis());
                holder.pickupDate.setText(model.getPickupDate());
                holder.dropoffDis.setText(model.getDropoffDis());
                holder.dropoffDate.setText(model.getDropoffDate());
                holder.amount.setText(model.getAmount().toString());

//                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        FirebaseDatabase.getInstance().getReference().child("PastTrip").child(getRef(position).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//
//                            }
//                        });
//                    }
//                });

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        dialog.setTitle("Delete");
                        dialog.setMessage("Are you Sure to Delete this Data?");
                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference().child("PastTrip").child(getRef(position).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getActivity(), "Data has been Deleted!", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            }
                        });
                        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog alertDialog = dialog.create();
                        alertDialog.show();

                    }
                });
//                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("PastTrip").child(getRef(position).getKey());
//                        dbref.removeValue();
//                        Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();
//                    }
//                });

            }


            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_trips, parent, false);
                ViewHolder viewHolder = new ViewHolder(view);
                return viewHolder;
            }
        };
        adapter.notifyDataSetChanged();
        adapter.startListening();
        tripsList.setAdapter(adapter);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView driverName;
        TextView phone;
        TextView dateTime;
        TextView pickupDis;
        TextView pickupDate;
        TextView dropoffDis;
        TextView dropoffDate;
        TextView amount;
        ImageView locationImage;
        TextView rides;
        ImageView btnDelete;
        TextView completed;
        Button btnFeedback;
        ImageView picPickup;
        ImageView dotLine;
        ImageView picDropoff;
        TextView lkr;
        View divider;
        View divider2;
        View divider3;
        LinearLayout cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            driverName = itemView.findViewById(R.id.driverName);
            phone = itemView.findViewById(R.id.phone);
            dateTime = itemView.findViewById(R.id.dateTime);
            pickupDis = itemView.findViewById(R.id.pickupDis);
            pickupDate = itemView.findViewById(R.id.pickupDate);
            dropoffDis = itemView.findViewById(R.id.dropoffDis);
            dropoffDate = itemView.findViewById(R.id.dropoffDate);
            amount = itemView.findViewById(R.id.amount);
            locationImage = itemView.findViewById(R.id.locationImage);
            rides = itemView.findViewById(R.id.rides);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            completed = itemView.findViewById(R.id.completed);
            btnFeedback = itemView.findViewById(R.id.btnFeedback);
            picPickup = itemView.findViewById(R.id.picPickup);
            dotLine = itemView.findViewById(R.id.dotLine);
            picDropoff = itemView.findViewById(R.id.picDropoff);
            lkr = itemView.findViewById(R.id.lkr);
            divider = itemView.findViewById(R.id.divider);
            divider2 = itemView.findViewById(R.id.divider2);
            divider3 = itemView.findViewById(R.id.divider3);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}