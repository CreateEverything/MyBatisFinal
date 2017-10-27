package com.kaishengit;

import com.github.pagehelper.PageHelper;
import com.kaishengit.entity.Book;
import com.kaishengit.entity.BookExample;
import com.kaishengit.mapper.BookMapper;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BookTest {
    SqlSession sqlSession;
    BookMapper bookMapper;
    @Before
    public void init(){
        sqlSession = MyBatisUtil.getSqlSession();
        bookMapper = sqlSession.getMapper(BookMapper.class);
    }
    public void close(){
        sqlSession.close();
    }
    @Test
    public void findByBookId(){

        Book book = bookMapper.selectByPrimaryKey(1);
        System.out.println(book.getBookName());
    }
    @Test
    public void findByBookName(){
//        查询时向接口方法中传入example对象
        BookExample bookExample = new BookExample();
        bookExample.createCriteria().andBookNameLike("%浮生%");
        List<Book> bookList = bookMapper.selectByExample(bookExample);
        System.out.println(bookList.get(1));
    }
    @Test
    public void findAll(){
//        分页只需加一条数据
        PageHelper.startPage(1,5);
//        查询全部时传入一个无条件的example对象
        BookExample bookExample = new BookExample();
        List<Book> bookList = bookMapper.selectByExample(bookExample);
        for(int i=0;i<bookList.size();i++){
            System.out.println(bookList.get(i));
        }
    }
}
