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
using System.Data;

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
            if (this.txtb_reportSource.Text != "") 
            {
                this.bton_filesearch.Enabled = false;
            }
            DataTable _Table = tools.GetExcelTableName(ofd.FileName);
            for (int i = 0; i != _Table.Rows.Count; i++)
            {
               
                this.drpd_tableName.Items.Add(new ListItem("i", _Table.Rows[i]["Table_Name"].ToString()));
                
            }
        }


        private void FormAddReportforms_Load(object sender, EventArgs e)
        {
            
            Init();
           
          
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
            this.drpd_reportType.Items.Add(new ListItem("once", "执行一次"));
            this.drpd_reportType.Items.Add(new ListItem("repeat", "重复多次"));


            //栏位规则绑定
            this.drpd_symbol.Items.Add(new ListItem(">", "大于"));
            this.drpd_symbol.Items.Add(new ListItem(">=", "大于等于"));
            this.drpd_symbol.Items.Add(new ListItem("<", "小于"));
            this.drpd_symbol.Items.Add(new ListItem("<=", "小于等于"));
            this.drpd_symbol.Items.Add(new ListItem("=", "等于"));
            this.drpd_symbol.Items.Add(new ListItem("!=", "不等于"));
            this.drpd_symbol.Items.Add(new ListItem("in", "包含"));
            this.drpd_symbol.Items.Add(new ListItem("not in", "不包含"));

            //频率类型绑定
            this.drpd_pinlv.Items.Add(new ListItem(" D", "每天"));
            this.drpd_pinlv.Items.Add(new ListItem(" W", "每周"));
            this.drpd_pinlv.Items.Add(new ListItem(" M", "每月"));

            //事件类型绑定
            this.drpd_timeType.Items.Add(new ListItem("Hou", "小时"));
            this.drpd_timeType.Items.Add(new ListItem("Min", "分钟"));   
        }

        private void drpd_reportType_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (this.drpd_reportType.SelectedItem.ToString() == "重复多次")
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
                this.drpd_pinlv_week.Visible = false;
                this.drpd_pinlv_day.Visible = true;
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
                this.drpd_pinlv_week.Visible = true;
                this.drpd_pinlv_day.Visible = false;
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
                this.drpd_pinlv_week.Visible = false;
                this.drpd_pinlv_day.Visible = true;

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

        private void bton_sure_Click(object sender, EventArgs e)
        {
          
            //创建Rule的Bll层对象
            syntec.BLL.ExcelRule ruleBll = new syntec.BLL.ExcelRule();
            //创建Rule的Model层对象
            syntec.Model.ExcelRule ruleModel = new syntec.Model.ExcelRule();
            //给ruleModel对象赋值
            ruleModel.reportSource = this.txtb_reportSource.Text;
            ruleModel.sheetName = this.drpd_tableName.SelectedItem.ToString();
            ruleModel.position = this.txtb_hang.Text.Trim() + "," + this.txtb_lei.Text.Trim();
            ListItem item = (ListItem)this.drpd_symbol.SelectedItem;
            ruleModel.symbol = item.ID;
            item = (ListItem)this.drpd_reportType.SelectedItem;
            ruleModel.planType = item.ID;        
            ruleModel.aim = this.txtb_aim.Text.ToString().Trim();

            if (this.txtb_attachmentSource.Text.Length != 0) 
            {
                ruleModel.attachmentSource = this.txtb_attachmentSource.Text.ToString();
            }
            ruleModel.email = this.txtb_email.Text.ToString().Trim();
            ruleModel.article = this.txtb_article.Text.ToString().Trim();
            //仅仅执行一次
            if (this.drpd_reportType.SelectedIndex == 0)
            {

                ruleModel.timeType = null;

                ruleModel.timeSpace = null;

                ruleModel.everydayFreSpace = null;

                TimeSpan s = new TimeSpan(this.dtme_OnceTime.Value.Hour, this.dtme_OnceTime.Value.Minute, this.dtme_OnceTime.Value.Second);

                ruleModel.startTime = s;

                ruleModel.startDate = this.dtme_OnceDateTime.Value;

             

                DateTime t = new DateTime();

                
                t = tools.AddTwoDate(this.dtme_OnceDateTime.Value, this.dtme_OnceTime.Value);

                //唯一一个不要修改的。！！！！！！！！！！！！！！！！
                ruleModel.nextDoTimeDate = t;

               
            }
            else 
            {
                ruleModel.planType="repeat";
                if (this.rbtn_cxTime_endTime.Checked)
                {
                    DateTime endTime =new  DateTime();
                    endTime.AddHours(this.dtme_lastEndTime.Value.Hour);
                    endTime.AddMinutes(this.dtme_lastEndTime.Value.Minute);
                    endTime.AddSeconds(this.dtme_lastEndTime.Value.Second);
                    ruleModel.endDate = this.dtme_cxTime_endTime.Value;
                }
              
                //按每日频率插入
                if (this.drpd_pinlv.SelectedIndex == 0) 
                {
                    ruleModel.timeType = "D";

                    ruleModel.timeSpace = this.drpd_pinlv_day.Value.ToString();
                    //要修改的。！！！！！！！！！！！！！！！！
                    ruleModel.nextDoTimeDate = this.dtme_cxTime_endTime.Value;
                   
                }
                //按每周频率插入
                if (this.drpd_pinlv.SelectedIndex == 1) 
                {
                    ruleModel.timeType = "W";
                    //拼接周,星期
                    ruleModel.timeSpace = this.drpd_pinlv_week.Value.ToString() + JudgeCheckbox();
                  
                    if (JudgeCheckbox() == "") 
                    {
                        MessageBox.Show("请至少选择一个星期日期！！！！");
                        return;
                    }
                    //要修改的。！！！！！！！！！！！！！！！！
                    ruleModel.nextDoTimeDate = this.dtme_cxTime_endTime.Value;
                   
                    
                }
                //按每月频率插入
                if (this.drpd_pinlv.SelectedIndex == 2) 
                {
                    ruleModel.timeType = "M";
                    //要修改的。！！！！！！！！！！！！！！！！
                    ruleModel.timeSpace = this.drpd_pinlv_day.Value.ToString() + "," + this.drpd_pinlv_month.Value.ToString();
                    ruleModel.nextDoTimeDate = this.dtme_cxTime_endTime.Value;
                    
                }
                DateTime d = new DateTime();
                d = tools.AddTwoDate(this.dtme_cxTime_startTime.Value, this.dtme_lastStartTime.Value);
                
                ruleModel.startDate = d;
                TimeSpan s1 = new TimeSpan(this.dtme_MoreTime.Value.Hour, this.dtme_MoreTime.Value.Minute, this.dtme_MoreTime.Value.Second);
                ruleModel.startTime = s1;

                if (tools.getnextToDateTime(ruleModel) != null)
                {
                    ruleModel.nextDoTimeDate = (DateTime)tools.getnextToDateTime(ruleModel);
                }
            }
            if (this.rbtn_mtpinlv_once.Checked)
            {
                ruleModel.everydayFreSpace = null;
            }
            else
            {

                ListItem i = (ListItem)drpd_timeType.SelectedItem;
                if (i.ID == null) 
                {
                    MessageBox.Show("请选择时间类型！！！！");
                    return;
                }
                ruleModel.everydayFreSpace = this.drpd_mtpinlv_time.Value.ToString() + i.ID;
            }
            
           

           

         

            ruleBll.Add(ruleModel);
            
        }

        private void drpd_symbol_SelectedIndexChanged(object sender, EventArgs e)
        {
            
        }

        private void lbel_position_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (this.folderBrowserDialog1.ShowDialog() == DialogResult.OK)
            {
                if (this.folderBrowserDialog1.SelectedPath.Trim() != "")
                    this.txtb_fileBName.Text = this.folderBrowserDialog1.SelectedPath.Trim();
                this.bton_search.Enabled = false;
                this.drpd_tableName.Enabled = false;
            }
 
        }

        private void bton_fileBName_Click(object sender, EventArgs e)
        {
            this.txtb_fileBName.Text = "";
            this.bton_search.Enabled = true;
            this.drpd_tableName.Enabled = true;
        }

        private void bton_fileClear_Click(object sender, EventArgs e)
        {
            this.txtb_reportSource.Text = "";
            this.bton_filesearch.Enabled = true;
        }

        private string JudgeCheckbox() 
        {
            string a = "";
            if (this.cbox_pinlv_Firstday.Checked) 
            {
                a += ",Firstday";
            }
            if (this.cbox_pinlv_Tuesday.Checked) 
            {
                a += ",Tuesday";
            }
            if (this.cbox_pinlv_Wednesday.Checked) 
            {
                a += ",Wednesday";
            }
            if (this.cbox_pinlv_Thursday.Checked) 
            {
                a += ",Thursday";
            }
            if (this.cbox_pinlv_Friday.Checked) 
            {
                a += ",Friday";
            }
            if (this.cbox_pinlv_Saturday.Checked) 
            {
                a += ",Saturday";
            }
            if (this.cbox_pinlv_Sunday.Checked) 
            {
                a += ",Sunday";
            }
            return a;
        }

      
      
      }

     

      
   }


