package tools;

import java.sql.Date;

import android.R.string;
// ��ͨ��serialNo�鵽������Ϣ 
public class Process {
	private String processId;		//�������id
	private String processName;		//������
	private Date createdTime;		//���̴���ʱ��
	private String requesterId;		//�����˹���
	private String requesterName;	//����������
	private String state;			//����״̬
	private String OID;				//����OID
	private String serialNo;		//���̵���
	private String subject;			//��������
	private String abortComment;	//����ȡ������
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
