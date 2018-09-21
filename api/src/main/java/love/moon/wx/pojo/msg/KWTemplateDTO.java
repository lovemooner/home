package love.moon.wx.pojo.msg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 模版消息
 * User: ndong
 * Date: 14-8-28
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class KWTemplateDTO implements Serializable{
  private Long id;
  private String publicNo;
  private String title;
  private String templateId;
  private String first;
  private String remark;
  private String topColor;
  private String firstColor;
  private String keyword1Color;
  private String keyword2Color;
  private String keyword3Color;
  private String remarkColor;

  public KWMsgTemplate toWXKWMsgTemplate(){
    KWMsgTemplate template=new KWMsgTemplate();
    template.setTemplate_id(getTemplateId());
    template.setFirst(getFirst());
    template.setRemark(getRemark());
    template.setTopcolor(getTopColor());
    template.setFirstColor(getFirstColor());
    template.setKeyword1Color(getKeyword1Color());
    template.setKeyword2Color(getKeyword2Color());
    template.setKeyword3Color(getKeyword3Color());
    template.setRemarkColor(getRemarkColor());
    return template;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPublicNo() {
    return publicNo;
  }

  public void setPublicNo(String publicNo) {
    this.publicNo = publicNo;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getTopColor() {
    return topColor;
  }

  public void setTopColor(String topColor) {
    this.topColor = topColor;
  }

  public String getFirstColor() {
    return firstColor;
  }

  public void setFirstColor(String firstColor) {
    this.firstColor = firstColor;
  }

  public String getKeyword1Color() {
    return keyword1Color;
  }

  public void setKeyword1Color(String keyword1Color) {
    this.keyword1Color = keyword1Color;
  }

  public String getKeyword2Color() {
    return keyword2Color;
  }

  public void setKeyword2Color(String keyword2Color) {
    this.keyword2Color = keyword2Color;
  }

  public String getKeyword3Color() {
    return keyword3Color;
  }

  public void setKeyword3Color(String keyword3Color) {
    this.keyword3Color = keyword3Color;
  }

  public String getRemarkColor() {
    return remarkColor;
  }

  public void setRemarkColor(String remarkColor) {
    this.remarkColor = remarkColor;
  }


}