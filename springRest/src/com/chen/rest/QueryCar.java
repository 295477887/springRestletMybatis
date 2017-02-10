package com.chen.rest;

import org.mybatis.spring.SqlSessionTemplate;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author chenjie
 * @version 1.0
 * @since 2017-02-09
 */
public class QueryCar extends ServerResource
{

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * get请求
     * 通过HttpServletRequest获取请求参数
     * */
    @Get("xml|json")
    public Representation getQueryCar()
    {
        HttpServletRequest httprequest = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String id = httprequest.getParameter("id");
        String name = httprequest.getParameter("name");
        System.out.println("id1="+id);
        System.out.println("name2="+name);
        Representation representation = new StringRepresentation("hello get");
        String sql = "select * from city_list where province_pinyin= ? ";
        SqlRowSet srs = jdbcTemplate.queryForRowSet(sql,new Object[]{"SD"});
        while(srs.next())
        {
            System.out.println(srs.getString("city_name"));
        }
        return 	representation;
    }
    /**
     * post请求
     * 通过Representation来获取请求参数
     * */
    @Post
    public Representation postQueryCar(Representation entity)
    {
        HttpServletRequest httprequest = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        Form form = new Form(entity);
        String id = form.getValues("id");
        String name = form.getFirstValue("name");
        System.out.println("id2="+id);
        System.out.println("name2="+name);
        Representation representation = new StringRepresentation("hello post");
        try
        {
            //查询1条结果
            IxintuiBean bean = (IxintuiBean)sqlSessionTemplate.selectOne("queryOneCar","733095477");
            System.out.println("selectOne="+bean);
            //查询map
            Map map = sqlSessionTemplate.selectMap("queryCarMap", "msgid");
            System.out.println("selectMap="+map);
            //查询list
            List<IxintuiBean> list = sqlSessionTemplate.selectList("queryCarList");
            for(int i=0;i<list.size();i++)
            {
                System.out.println(i+"==="+list.get(i));
            }
            //修改update
            sqlSessionTemplate.update("updateCity");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return 	representation;
    }

}
