using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.OleDb;
using System.IO;

namespace Winform_ExcelWarning
{
    public partial class FormAddReportforms : Form
    {
        //OleDbConnection cn = new OleDbConnection(tools.sConnectionString);
        // cn.Open();//创建TestSheet工作表

        // string sqlInsert = "INSERT INTO 报表规则表 VALUES(" + "'" + this.txtb_reportSource.Text.Trim() + "'," + "'" + this.txtb_reportlanwei.Text.Trim() + "'" + "," + "'" + this.txtb_reportguize.Text.Trim() + "'" + "," + "'" + this.drpd_email.SelectedItem.ToString() + "'" + "," + "'" + email + "'" + ")";
        // OleDbCommand myCommandd = new OleDbCommand(sqlInsert, cn);
        // myCommandd.ExecuteNonQuery();
        // cn.Close();              
        public FormAddReportforms()
        {
            InitializeComponent();
        }

        private void bton_search_Click(object sender, EventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            ofd.ShowDialog();
            this.txtb_reportSource.Text = ofd.FileName;
        }


        private void FormAddReportforms_Load(object sender, EventArgs e)
        {
            Init();
            OleDbConnection objConn = new OleDbConnection(tools.sConnectionString);
            objConn.Open();
            OleDbCommand objCmdSelect = new OleDbCommand("SELECT * FROM [目标用户信息表]", objConn);
            OleDbDataAdapter objAdapter1 = new OleDbDataAdapter();
            objAdapter1.SelectCommand = objCmdSelect;
            DataSet objDataset1 = new DataSet();
            //将Excel中数据填充到数据集
            objAdapter1.Fill(objDataset1, "Persons");
            objConn.Close();
            DataTable dt=objDataset1.Tables[0];
            //遍历行
           // foreach(DataRow dr in dt.Rows){  //遍历列             
             //       Person person = new Person();
              //      person.Name = dr[0].ToString();
               //     person.Email = dr[1].ToString();
                //    tools.persons.Add(person);               
             // }
          //  MessageBox.Show(tools.persons[0].Email);
         //   foreach (Person p in tools.persons)
         //   {
         //       this.drpd_personName.Items.Add(new ListItem("Name", p.Name));
         //   }
        }

        private void bton_back_Click(object sender, EventArgs e)
        {
            FormMain formMain = new FormMain();
            formMain.Show();
            this.Hide();
        }

        private void bton_restart_Click(object sender, EventArgs e)
        {

        }

        private void Init() 
        {
            //计划类型绑定
            this.drpd_reportType.Items.Add(new ListItem("执行一次","执行一次"));
            this.drpd_reportType.Items.Add(new ListItem("重复执行","重复执行"));


            //栏位规则绑定
            this.drpd_symbol.Items.Add(new ListItem("大于", "大于"));
            this.drpd_symbol.Items.Add(new ListItem("大于等于", "大于等于"));
            this.drpd_symbol.Items.Add(new ListItem("小于", "小于"));
            this.drpd_symbol.Items.Add(new ListItem("小于等于", "小于等于"));
            this.drpd_symbol.Items.Add(new ListItem("等于", "等于"));
            this.drpd_symbol.Items.Add(new ListItem("不等于", "不等于"));
            this.drpd_symbol.Items.Add(new ListItem("包含", "包含"));
            this.drpd_symbol.Items.Add(new ListItem("不包含", "不包含"));

            //频率类型绑定
            this.drpd_pinlv.Items.Add(new ListItem(" 每天", "每天"));
            this.drpd_pinlv.Items.Add(new ListItem(" 每周", "每周"));
            this.drpd_pinlv.Items.Add(new ListItem(" 每月", "每月"));

            //
            this.drpd_timeType.Items.Add(new ListItem("小时", "小时"));
            this.drpd_timeType.Items.Add(new ListItem("分钟", "分钟"));   
        }

        private void drpd_reportType_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (this.drpd_reportType.SelectedItem.ToString() == "重复执行")
            {
                this.gbox_once.Enabled = false;   
                this.gbox_more.Enabled = true;
                this.gbox_everydaymore.Enabled = true;
                this.gbox_moreCTime.Enabled = true;
               
            }
            else 
            {
                this.gbox_once.Enabled = true;
                this.gbox_more.Enabled = false;
                this.gbox_everydaymore.Enabled = false;
                this.gbox_moreCTime.Enabled = false;
            }
        }

        private void setGroupReadonlyTrue(Boolean b,GroupBox g) 
        {
           
                foreach (Control c in g.Controls)
                {
                    if(b)
                    {
                        c.Enabled = false;
                    }
                    else
                    {
                        c.Enabled = true;
                    }
                }
            }

        private void drpd_timeType_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (this.drpd_timeType.SelectedItem.ToString() == "小时")
            {
                this.drpd_mtpinlv_time.Maximum = 24;
            }
            else 
            {
                this.drpd_mtpinlv_time.Maximum = 60;
            }
        }

        private void rbtn_mtpinlv_once_CheckedChanged(object sender, EventArgs e)
        {
            this.dtme_MoreTime.Enabled = true;
            this.drpd_mtpinlv_time.Enabled = false;
            this.drpd_timeType.Enabled = false;
        }

        private void rbtn_mtpinlv_more_CheckedChanged(object sender, EventArgs e)
        {
            this.dtme_MoreTime.Enabled = false;
            this.drpd_mtpinlv_time.Enabled = true;
            this.drpd_timeType.Enabled = true;
        }

        private void rbtn_cxshijian_endtime_CheckedChanged(object sender, EventArgs e)
        {
            this.dtme_cxTime_endTime.Enabled = true;
        }

        private void rbtn_cxTime_noendTime_CheckedChanged(object sender, EventArgs e)
        {
            this.dtme_cxTime_endTime.Enabled = false;
        }

        private void drpd_pinlv_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (this.drpd_pinlv.SelectedItem.ToString() == "每天") 
            {
                this.lbel_pinlv_day.Visible = true;
                this.lbel_pinlv_week.Visible = false;
                this.lbel_pinlv_mei.Visible = false;
                this.drpd_pinlv_month.Visible = false;
                this.lbel_pinlv_month.Visible = false;
                foreach (Control c in gbox_more.Controls) 
                {
                    if (c.GetType().ToString() == "System.Windows.Forms.CheckBox")
                    {
                        c.Visible = false;
                    }
                }
            }
            if (this.drpd_pinlv.SelectedItem.ToString() == "每周")
            {
                this.lbel_pinlv_day.Visible = false;
                this.lbel_pinlv_week.Visible = true;
                this.lbel_pinlv_mei.Visible = false;
                this.drpd_pinlv_month.Visible = false;
                this.lbel_pinlv_month.Visible = false;
                foreach (Control c in gbox_more.Controls)
                {
                    if (c.GetType().ToString() == "System.Windows.Forms.CheckBox") 
                    {
                        c.Visible = true;
                    }
                }
            }
            if (this.drpd_pinlv.SelectedItem.ToString() == "每月")
            {
                this.lbel_pinlv_day.Visible = true;
                this.lbel_pinlv_week.Visible = false;
                this.lbel_pinlv_mei.Visible = true;
                this.drpd_pinlv_month.Visible = true;
                this.lbel_pinlv_month.Visible = true;

                foreach (Control c in gbox_more.Controls)
                {
                    if (c.GetType().ToString() == "System.Windows.Forms.CheckBox")
                    {
                        c.Visible = false;
                    }
                }
            }
        }

        private void bton_searchAttachment_Click(object sender, EventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            ofd.ShowDialog();
            this.txtb_attachmentSource.Text = ofd.FileName;
        }


      

    

      

  
      

        
        

        
      

      
         
        }

     

      
    }


