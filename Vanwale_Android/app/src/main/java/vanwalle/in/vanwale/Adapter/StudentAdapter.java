package vanwalle.in.vanwale.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vanwalle.in.vanwale.Dashboard;
import vanwalle.in.vanwale.Fragment.PackageFragment;
import vanwalle.in.vanwale.Fragment.StudentFragment;
import vanwalle.in.vanwale.Interface.ItemClickListener;
import vanwalle.in.vanwale.R;
import vanwalle.in.vanwale.Retrofit.ApiHelperClass;
import vanwalle.in.vanwale.Retrofit.ApiWebservices;
import vanwalle.in.vanwale.Retrofit.CustomNetwork;
import vanwalle.in.vanwale.Utils.MySharedPreferences;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    Context mContext;
    Button submit;

    MySharedPreferences mySharedPreferences;

    public StudentAdapter(Context mContext,Button button)
    {
        submit=button;
        this.mContext=mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.student_detail, viewGroup, false);

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ((Dashboard) mContext).clearBackStack();
        ((Dashboard) mContext).changeFragment(new PackageFragment(), PackageFragment.TAG);
    }
});
        mySharedPreferences=new MySharedPreferences(mContext);
        return new ViewHolder(view);
    }
    private void setStudent(String i,String username, String Class, String section, String age,String rollno,String school_name,String start_date) {
        if (CustomNetwork.isNetworkAvailable(mContext)) {

            ApiWebservices apiWebservices = ApiHelperClass.getClient().create(ApiWebservices.class);
            Call<String> call = apiWebservices.setStudent("1",i,username, Class, section, age,rollno,school_name,start_date);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String modelRes = response.body();
                    Toast.makeText(mContext,modelRes,Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        } else {

        }
    }



    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder viewHolder, int i) {
      //  setStudent(String.valueOf(i),viewHolder.name.getText().toString(),viewHolder.clas.getText().toString(),viewHolder.section.getText().toString(),viewHolder.age.getText().toString(),viewHolder.roll_no.getText().toString(),viewHolder.school_name.getText().toString(),viewHolder.start_date.getText().toString());
    }

    @Override
    public int getItemCount() {
        return 2;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ItemClickListener itemClickListener;
        public TextInputEditText name,clas,section,age,roll_no,start_date,school_name;
        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextInputEditText)itemView.findViewById(R.id.name1);
            clas=(TextInputEditText)itemView.findViewById(R.id.class_student1);
            section=(TextInputEditText)itemView.findViewById(R.id.section1);
            age=(TextInputEditText)itemView.findViewById(R.id.age_student);
            school_name=(TextInputEditText)itemView.findViewById(R.id.college_name);
            roll_no=(TextInputEditText)itemView.findViewById(R.id.rollno_student);
            start_date=(TextInputEditText)itemView.findViewById(R.id.start_date2);
            itemView.setOnClickListener(this);
//            rvCatList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.HORIZONTAL,false));
//            categoryBikeListAdapter=new CategoryBikeListAdapter(mContext);
//            rvCatList.setAdapter(categoryBikeListAdapter);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {

            itemClickListener.onClick(v,getAdapterPosition(),false);
        }
    }
}

