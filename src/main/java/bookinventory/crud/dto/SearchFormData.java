package bookinventory.crud.dto;

public class SearchFormData {
  private String keyword;

  public SearchFormData(){

  }

  public SearchFormData(String keyword){
    this.keyword = keyword;
  }
  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
