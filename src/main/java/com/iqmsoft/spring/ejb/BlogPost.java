

package com.iqmsoft.spring.ejb;


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;



@Entity
public class BlogPost {

    /** The id value. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /** The author, unused. */
    private String author;

    /** The title. */
    private String title;

    /** Short content of the article. */
    private String shortContent;

    /** The article content. */
  //  @Lob
   // @Basic(fetch = FetchType.LAZY)
   // private String content;


    public BlogPost() {
    	
    }
    
    
    /**
     * Gets the id value.
     *
     * @return the id value.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id value.
     *
     * @param id the id value.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the author.
     *
     * @return the author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     *
     * @param author the author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the content.
     *
     * @return the content.
     */
  //  public String getContent() {
        //return content;
    //}

    /**
     * Sets the content.
     *
     * @param content the content.
     */
   // public void setContent(String content) {
       // this.content = content;
   // }

    /**
     * Gets the title.
     *
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the short content.
     *
     * @return the short content.
     */
    public String getShortContent() {
        return shortContent;
    }

    /**
     * Sets the short content.
     *
     * @param shortContent the short content.
     */
    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }
}
