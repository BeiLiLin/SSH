package po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(schema="student")
public class Students {
	private String stuID;
	private String stuName;
	private String sex;
	private Date birthday;

	public Students() {
		
	}
	public Students(String stuID, String stuName, String sex, Date birthday)
	{
		super();
		this.stuID = stuID;
		this.stuName = stuName;
		this.sex = sex;
		this.birthday = birthday;
	}
	@Id
	@GeneratedValue(generator="stuID")//手动赋值
	@GenericGenerator(name="stuID",strategy="assigned")
	public String getStuID() {
		return stuID;
	}

	public void setStuID(String stuID) {
		this.stuID = stuID;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString()
	{
		return "Students [stuID=" + stuID + ", stuName=" + stuName + ", sex=" + sex + ", birthday=" + birthday + "]";
	}
	
	
	
}
