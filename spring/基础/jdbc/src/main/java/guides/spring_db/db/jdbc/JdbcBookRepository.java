package guides.spring_db.db.jdbc;

import guides.spring_db.db.BookRepository;
import guides.spring_db.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;

@Repository
public class JdbcBookRepository implements BookRepository {
    //SQL语句必须记得以分号结束；
    private static final String SQL_INSERT_BOOK =
            "INSERT INTO book(book_name,author,create_by,create_time,modify_by,modify_time) VALUES (?,?,?,?,?,?);";
    private static final String SQL_UPDATE_BOOK =
            "UPDATE BOOK SET book_name=?,author=?,modify_by=?,modify_time=? where book_id=?;";
    private static  final String SQL_FIND_BOOK=
            "SELECT book_id,book_name,author,create_by,create_time,modify_by,modify_time FROM book where book_id=?;";

    @Autowired
    private DataSource dataSource;

    //    在这段代码中，我们竟然捕获SQLException捕获了2次，这是因为connection = dataSource.getConnection();
//      preparedStatement.execute();preparedStatement.close();，connection.close();都会抛出检查型异常
//      SQLException，所以方法中必须捕获，否则会导致编译不通过
/*    @Override
    public void addBook(Book book) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_BOOK);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getCreateBy());
            preparedStatement.setTimestamp(4, new Timestamp(calendar.getTimeInMillis()));
            preparedStatement.setString(5, book.getModifyBy());
            preparedStatement.setTimestamp(6, new Timestamp(calendar.getTimeInMillis()));

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("向Book中添加用户"
                    + book.getBookName() + "的信息失败");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
    }*/

    //重构上述代码

    @Autowired
    private JdbcOperations jdbcOperations;
    @Override
    public void addBook(Book book) {
        jdbcOperations.update(SQL_INSERT_BOOK,book.getBookName(),
                book.getAuthor(),
                book.getCreateBy(),
                new Date(System.currentTimeMillis()),
                book.getModifyBy(),
                new Date(System.currentTimeMillis()));
    }

/*    @Override
    public void updateBook(Book book) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_BOOK);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getModifyBy());
            preparedStatement.setTimestamp(4, new Timestamp(calendar.getTimeInMillis()));
            preparedStatement.setLong(5, book.getBookId());

            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println("根据book_id修改书籍信息失败！");
            e.printStackTrace();
        }finally {
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }catch (SQLException e){
                System.out.println("无法断开连接！");
                e.printStackTrace();
            }
        }

    }*/
    //更新覆写
    @Override
    public void updateBook(Book book){
        jdbcOperations.update(SQL_UPDATE_BOOK, book.getBookName(),
                book.getAuthor(),
                book.getModifyBy(),
                new Timestamp(System.currentTimeMillis()),
                book.getBookId());
    }


   /* @Override
    public Book findBook(long book_id) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        ResultSet resultSet=null;
        Book book=null;
        try{
            connection=dataSource.getConnection();
            preparedStatement=connection.prepareStatement(SQL_FIND_BOOK);
            preparedStatement.setLong(1,book_id);

            resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                book =new Book();
                book.setBookId(resultSet.getLong("book_id"));
                book.setBookName((resultSet.getString("book_name")));
                book.setAuthor((resultSet.getString("author")));
                book.setCreateBy((resultSet.getString("create_by")));
                book.setCreateTime(resultSet.getTimestamp("create_time"));
                book.setModifyBy((resultSet.getString("modify_by")));
                book.setModifyTime(resultSet.getTimestamp("modify_time"));
            }
        }catch (SQLException e){
            System.out.println("根据book_id查询书籍信息失败！");
            e.printStackTrace();
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }catch (SQLException e){
                System.out.println("无法断开连接！");
                e.printStackTrace();
            }
        }
        return book;

    }*/

   //查找覆写
    @Override
    public Book findBook(long book_id) {
        return jdbcOperations.queryForObject(SQL_FIND_BOOK, new BookRowMapper(),book_id);
    }
    private static final class BookRowMapper implements RowMapper<Book>{
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book =new Book();
            book.setBookId(resultSet.getLong("book_id"));
            book.setBookName((resultSet.getString("book_name")));
            book.setAuthor((resultSet.getString("author")));
            book.setCreateBy((resultSet.getString("create_by")));
            book.setCreateTime(resultSet.getTimestamp("create_time"));
            book.setModifyBy((resultSet.getString("modify_by")));
            book.setModifyTime(resultSet.getTimestamp("modify_time"));
            return book;
        }
    }
}
