package uni.ocad.bcp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
@Repository
public class BcpDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public String getTipoSImunbyCOd(){
		String sql = "select count(*) from inscripcion where figmn=true and created_at>'2017-10-25 00:0:00';";
	
		String rowCount = jdbcTemplate.queryForObject(sql, String.class);
	
	  return rowCount;
	}
}
