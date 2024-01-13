package com.vladceresna.netget.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vladceresna.netget.pojos.SitesRecordInBase;
import com.vladceresna.netget.support.SL;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Route("")
@PageTitle("Netget!")
public class SearchView extends VerticalLayout {
    private TextField textField;
    private Grid<SitesRecordInBase> grid;
    public SearchView(){
        setSizeFull();
        setAlignItems(Alignment.START);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(
                new HorizontalLayout(
                        textField = new TextField(){{setPlaceholder("Глобальный поиск :)");}},
                        new Button(){{
                            setPrefixComponent(VaadinIcon.SEARCH.create());
                            addClickListener((ComponentEventListener<ClickEvent<Button>>) event -> {
                                JedisPooled j = new JedisPooled();
                                Set<String> keys = j.keys("page:*");
                                int keyssize = keys.size();
                                ArrayList<SitesRecordInBase> pages = new ArrayList<>();
                                for (int i = 0; i< keyssize; i++){
                                    try {
                                        SitesRecordInBase sitesRecordInBase = (SitesRecordInBase) SL.JtORiB(j.jsonGet("page:" + i));
                                        String title = sitesRecordInBase.getTitle();
                                        if (!title.equals("")) if (sitesRecordInBase.getTitle().contains(textField.getValue())) {
                                            pages.add(sitesRecordInBase);
                                        }
                                    }catch (Exception e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                                grid.setItems(pages);
                            });
                        }}
                ),
                grid = new Grid<>(SitesRecordInBase.class, false){{
                    addColumn(SitesRecordInBase::getTitle).setHeader("Title");
                    addColumn(SitesRecordInBase::getUrl).setHeader("Url");
                    addItemClickListener((ComponentEventListener<ItemClickEvent<SitesRecordInBase>>) event -> {
                        UI.getCurrent().getPage().open(event.getItem().getUrl());
                    });
                }}
        );
    }
}
