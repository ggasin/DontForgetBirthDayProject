package com.example.dontforgetbirthdayproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {
    MainActivity mainActivity;
    //onAttach 는 fragment가 activity에 올라온 순간
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }
    private ArrayList<ItemData> itemList;
    private HomeAdapter homeAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextView textView;
    private Spinner group_spinner;
    Button item_add_btn;
    String userId,selectedGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);
        String URL = "http://dfmbd.ivyro.net/RecyclerDB.php";

        recyclerView = (RecyclerView)rootView.findViewById(R.id.home_birth_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.scrollToPosition(0);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        itemList = new ArrayList<>();


        item_add_btn = (Button)rootView.findViewById(R.id.item_add_btn);
        textView = rootView.findViewById(R.id.textView3);
        group_spinner = rootView.findViewById(R.id.group_spinner);




        //그룹 저장한 spinner 이벤트
        group_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedGroup = adapterView.getItemAtPosition(position).toString();
                mainActivity.selectedGroup = selectedGroup;
                textView.setText(mainActivity.selectedGroup);
                loadDB(URL,adapterView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //db로부터 데이터를 가져와 recyclerView에 바인딩
        loadDB(URL, group_spinner.getSelectedItem().toString());


        item_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity에 있는 onFragmentChange 함수에 접근
                PopupMenu popup= new PopupMenu(getActivity().getApplicationContext(), view);//v는 클릭된 뷰를 의미

                popup.getMenuInflater().inflate(R.menu.add_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.add_group_menu:
                                Toast.makeText(getActivity().getApplicationContext(), "그룹추가", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.add_item_menu:
                                Log.d("selectedGroup on home1",selectedGroup);
                                Log.d("selectedGroup on home1",mainActivity.selectedGroup);
                                mainActivity.onFragmentChange(1);
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popup.show();

            }
        });

        return rootView;
    }


    public void loadDB(String url,String group){
        StringRequest request = new StringRequest(
                            Request.Method.POST,
                            url,
                new Response.Listener<String>() {  //응답을 문자열로 받아서 여기다 넣어달란말임(응답을 성공적으로 받았을 떄 이메소드가 자동으로 호출됨
                        @Override
                        public void onResponse(String response) {
                            itemList.clear();
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                homeAdapter = new HomeAdapter(getActivity().getApplicationContext(),itemList);
                                recyclerView.setAdapter(homeAdapter);
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String name = jsonObject.getString("itemName"); //no가 문자열이라서 바꿔야함.
                                    Log.d("스트이름 : ", name);
                                    String so_birth = jsonObject.getString("itemSolarBirth");
                                    Log.d("스트양력 : ", so_birth);
                                    String lu_birth = jsonObject.getString("itemLunarBirth");
                                    Log.d("스트음력 : ", lu_birth);
                                    String memo = jsonObject.getString("itemMemo");
                                    Log.d("스트메모 : ", memo);
                                    String group = jsonObject.getString("itemGroup");
                                    Log.d("스트그룹: ", group);
                                    int is_alram_on = jsonObject.getInt("itemAlramOn");
                                    Log.d("스트알람 : ", String.valueOf(is_alram_on));
                                    ItemData itemData= new ItemData(name, group,so_birth, lu_birth, memo, is_alram_on); // 첫 번째 매개변수는 몇번째에 추가 될지, 제일 위에 오도록
                                    itemList.add(itemData);
                                    Log.d("스트 어레이 : ",itemList.get(i).getTv_item_group());
                                    homeAdapter.notifyItemInserted(i);
                                    Log.d("스트 어뎁터 어레이 길이 : ",String.valueOf(homeAdapter.getItemCount()));
                                    Log.d("스트 어레이길이",String.valueOf(itemList.size()));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                            new Response.ErrorListener(){ //에러발생시 호출될 리스너 객체
                        @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            //만약 POST 방식에서 전달할 요청 파라미터가 있다면 getParams 메소드에서 반환하는 HashMap 객체에 넣어줍니다.
            //이렇게 만든 요청 객체는 요청 큐에 넣어주는 것만 해주면 됩니다.
            //POST방식으로 안할거면 없어도 되는거같다.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("itemId", mainActivity.userId);
                params.put("itemGroup",group);
                return params;
            }
        };
        //실제 요청 작업을 수행해주는 요청큐 객체 생성
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        //요청큐에 요청 객체 생성
        requestQueue.add(request);
    }
    /*
    public void loadDB(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, URL, null, new Response.Listener<JSONArray>() {
            //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
            @Override
            public void onResponse(JSONArray response) {
                //파라미터로 응답받은 결과 JsonArray를 분석
                itemList.clear();
                homeAdapter.notifyDataSetChanged();
                Log.d("길이 : ",String.valueOf(response.length()));
                try {
                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject= response.getJSONObject(i);
                        String name = jsonObject.getString("itemName"); //no가 문자열이라서 바꿔야함.
                        Log.d("스트이름 : ",name);
                        String so_birth=jsonObject.getString("itemSolarBirth");
                        Log.d("스트양력 : ",so_birth);
                        String lu_birth=jsonObject.getString("itemLunarBirth");
                        Log.d("스트음력 : ",lu_birth);
                        String memo=jsonObject.getString("itemMemo");
                        Log.d("스트메모 : ",memo);
                        String group = jsonObject.getString("itemGroup");
                        Log.d("스트그룹: ",group);
                        int is_alram_on=jsonObject.getInt("itemAlramOn");
                        Log.d("스트알람 : ",String.valueOf(is_alram_on));
                        ItemData itemData= new ItemData(name, group,so_birth, lu_birth, memo, is_alram_on); // 첫 번째 매개변수는 몇번째에 추가 될지, 제일 위에 오도록
                        itemList.add(itemData);
                        homeAdapter.notifyItemInserted(i);
                    }
                } catch (JSONException e) {e.printStackTrace();}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }

        });
        //실제 요청 작업을 수행해주는 요청큐 객체 생성
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);
    }
     */

}