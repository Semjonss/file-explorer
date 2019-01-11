package models.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.ListModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class FilterState implements ListModel {

  private final Set<String> whiteList = new TreeSet<>();
  private final List<String> data = new ArrayList<>();
  private EventListenerList listenerList = new EventListenerList();

  public FilterState(Set<String> data) {
    this.whiteList.addAll(data);
    this.data.addAll(data);
  }

  public void clear() {
    whiteList.clear();
    data.clear();
  }

  public void setValues(Set<String> whiteList, Set<String> data) {
    this.whiteList.clear();
    this.whiteList.addAll(whiteList);
    this.data.clear();
    this.data.addAll(data);
  }
  public void setValues(Set<String> data) {
    this.whiteList.clear();
    this.whiteList.addAll(data);
    this.data.clear();
    this.data.addAll(data);
  }

  public Set<String> getWhiteList() {
    return whiteList;
  }

  public List<String> getData() {
    return data;
  }


  public void setWhiteList(Set<String> whiteList) {
    this.whiteList.clear();
    this.whiteList.addAll(whiteList);
  }

  public void setData(List<String> data) {
    this.data.clear();
    this.data.addAll(data);
  }


  public boolean isCheckedIndex(int index) {
    return include(data.get(index));
  }

  public boolean include(String value) {
    return whiteList.isEmpty() || whiteList.contains(value);
  }

  public void changeStateIndex(int index) {
    String key = data.get(index);
    if (whiteList.contains(key)) {
      whiteList.remove(key);
    } else {
      whiteList.add(key);
    }

    if(whiteList.isEmpty()){
      whiteList.addAll(data);
    }
    fireListDataChanged();
  }


  @Override
  public int getSize() {
    return data.size();
  }

  @Override
  public Object getElementAt(int index) {
    return data.get(index);
  }

  @Override
  public void addListDataListener(ListDataListener l) {
    listenerList.add(ListDataListener.class, l);
  }

  @Override
  public void removeListDataListener(ListDataListener l) {
    listenerList.remove(ListDataListener.class, l);
  }


  public void fireListDataChanged() {

    for (ListDataListener listener : listenerList.getListeners(ListDataListener.class)) {
      ListDataEvent e = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
      listener.contentsChanged(e);
    }
  }


}
