package tasks.com.booklistingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   private static final int MENU_ITEM_VIEW_TYPE = 0;
   private static final int NATIVE_EXPRESS_AD_VIEW_TYPE = 1;
    private List  listItems;
    private Context context;

    public Adapter( Context context,List<Object> listItems) {
        this.context=context;
        this.listItems =   listItems;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView bookNameItem;
        private TextView authorNameItem;
        private TextView descriptionItem;
        private LinearLayout layout;

        ItemViewHolder(View view) {
            super(view);

            bookNameItem = (TextView) view.findViewById(R.id.book_name);
            authorNameItem = (TextView) view.findViewById(R.id.author_name);
            descriptionItem = (TextView) view.findViewById(R.id.description);
            layout=(LinearLayout) view.findViewById(R.id.layout);

        }
    }
  /*  public void filterList(ArrayList<String> filterNames) {

        this.listItems = filterNames;
        notifyDataSetChanged();
    }*/

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:

            default:
                View ItemLayoutView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.list_layout, viewGroup, false);
                return new ItemViewHolder(ItemLayoutView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
            default:
                ItemViewHolder holder1 = (ItemViewHolder) holder;
                Pojo item = (Pojo) listItems.get(position);
                holder1.bookNameItem.setText(item.getBookName());
                holder1.authorNameItem.setText(item.getAuthorNmae());
                holder1.descriptionItem.setText(item.getDescription());
                holder1.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(position==0) {
                            String url="https://www.amazon.com/Head-First-Java-Kathy-Sierra/dp/0596009208";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            context.startActivity(i);
                        }
                        if(position==1){
                            String url="https://www.amazon.com/Thinking-Java-4th-Bruce-Eckel/dp/0131872486";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            context.startActivity(i);
                        }
                        if(position==2){
                            String url="https://beginnersbook.com/2013/05/java-introduction/";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            context.startActivity(i);
                        }
                        if(position==3){
                            String url="https://www.amazon.com/Java-How-Program-9th-Deitel/dp/0132575663";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            context.startActivity(i);
                        }
                        if(position==4){
                            String url="http://shop.oreilly.com/product/0636920030775.do";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            context.startActivity(i);
                        }
                        if(position==5){
                            String url="https://www.amazon.com/Effective-Java-3rd-Joshua-Bloch/dp/0134685997";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            context.startActivity(i);
                        }
                        if(position==6){
                            String url="https://www.amazon.com/Core-Java-I-Fundamentals-10th/dp/0134177304";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            context.startActivity(i);
                        }


                    }
                });


        }
    }

    }

