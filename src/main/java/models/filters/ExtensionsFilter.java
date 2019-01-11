package models.filters;

import constants.Constants;
import javax.swing.RowFilter;

public class ExtensionsFilter extends RowFilter<Object, Object> {

  private FilterState filterState;

  public ExtensionsFilter(FilterState filterState) {
    this.filterState = filterState;
  }


  @Override
  public boolean include(Entry<?, ?> entry) {
    return filterState.include((String) entry.getValue(Constants.FILTERED_COLUMN));
  }


}
