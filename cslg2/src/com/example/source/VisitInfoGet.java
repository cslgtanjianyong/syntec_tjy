package com.example.source;

import java.util.Date;

public class VisitInfoGet {
		private int id;//id��ʶ
		private String visitId;//�ݷ�ID��
		private Date visitDay;//�ݷ�����
		private String goal;//�ݷ�Ŀ��
		private String content;//�ݷ�����
		private String customerName;//�ͻ�����
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public VisitInfoGet()
		{
			
		}
		public VisitInfoGet(int id, String visitId, Date visitDay, String goal,
				String content) {
			super();
			this.id = id;
			this.visitId = visitId;
			this.visitDay = visitDay;
			this.goal = goal;
			this.content = content;
		}
		public VisitInfoGet(String visitId, Date visitDay, String goal, String content) {
			super();
			this.visitId = visitId;
			this.visitDay = visitDay;
			this.goal = goal;
			this.content = content;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getVisitId() {
			return visitId;
		}
		public void setVisitId(String visitId) {
			this.visitId = visitId;
		}
		public Date getVisitDay() {
			return visitDay;
		}
		public void setVisitDay(Date visitDay) {
			this.visitDay = visitDay;
		}
		public String getGoal() {
			return goal;
		}
		public void setGoal(String goal) {
			this.goal = goal;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
	}


