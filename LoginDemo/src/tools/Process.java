package tools;

import java.sql.Date;

import android.R.string;
// 可通过serialNo查到流程信息 
public class Process {
	private String processId;		//流程类别id
	private String processName;		//流程名
	private Date createdTime;		//流程创建时间
	private String requesterId;		//申请人工号
	private String requesterName;	//申请人姓名
	private String state;			//流程状态
	private String OID;				//流程OID
	private String serialNo;		//流程单号
	private String subject;			//流程主题
	private String abortComment;	//流程取消理由
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getRequesterId() {
		return requesterId;
	}
	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}
	public String getRequesterName() {
		return requesterName;
	}
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOID() {
		return OID;
	}
	public void setOID(String oID) {
		OID = oID;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAbortComment() {
		return abortComment;
	}
	public void setAbortComment(String abortComment) {
		this.abortComment = abortComment;
	}
}
