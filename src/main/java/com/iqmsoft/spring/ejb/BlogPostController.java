


package com.iqmsoft.spring.ejb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import java.util.List;


@Controller
@RequestMapping(value="/blogposts")
public class BlogPostController {

    
    @EJB(mappedName="java:app/Spring3EJB/BlogPostDaoBean")
    private BlogPostDaoBean blogPostDao;

  
    @Autowired
    private SpamFilterService spamFilterService;

   
    @RequestMapping(value = "/new", method= RequestMethod.GET)
    public ModelAndView getCreateForm() {
        ModelAndView mav = new ModelAndView("new");
        mav.addObject(new BlogPost());
        return mav;
    }

    
    @RequestMapping(method=RequestMethod.POST)
    public String create(BlogPost blogPost, BindingResult result) throws SpamException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException {
      //  String content = blogPost.getContent();
       // boolean isSpam = spamFilterService.checkSpam(content);
       // if (isSpam) {
            //throw new SpamException();
       // }
        String shortContent;
      //  if (content.length() > 255) {
            //shortContent = content.substring(0, 255);
       // } else {
           // shortContent = content;
       // }
       // blogPost.setShortContent(shortContent);
       
		blogPostDao.persist(blogPost);
       
        return "redirect:/blogposts/";
    }

   
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public ModelAndView getArticles() {
        ModelAndView mav = new ModelAndView("posts");
        List<BlogPost> blogPostList = blogPostDao.findAll();
        mav.addObject("articles", blogPostList);
        return mav;
    }

    
    @RequestMapping(value="{id}", method=RequestMethod.GET)
    public ModelAndView getView(@PathVariable Integer id) {
        BlogPost blogPost = blogPostDao.find(id);
        if (blogPost == null) {
            throw new ResourceNotFoundException();
        }
        ModelAndView mav = new ModelAndView("article");
        mav.addObject("article", blogPost);
        return mav;
    }

  
    @ExceptionHandler(SpamException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleSpamException(HttpServletRequest request, HttpServletResponse response,
                                            HttpSession session, SpamException e) {
        ModelMap model = new ModelMap();
        return new ModelAndView("/spam", model);
    }
}
