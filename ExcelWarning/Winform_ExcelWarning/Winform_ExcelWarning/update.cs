using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Winform_ExcelWarning
{
    public partial class update : Form
    {
        private syntec.Model.ExcelRule _rule;
        public update(syntec.Model.ExcelRule rule)
        {
            _rule = rule;
         
            InitializeComponent();

            dataInit();
            RuleInit();
        }

        private void bton_sure_Click(object sender, EventArgs e)
        {

        }
        private void dataInit()
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
        private void RuleInit()
        {
            this.txtb_reportSource.Text = _rule.reportSource;


            DataTable _Table = tools.GetExcelTableName(this.txtb_reportSource.Text);
            for (int i = 0; i != _Table.Rows.Count; i++)
            {
                if (_Table.Rows[i]["Table_Name"].ToString().Substring(0, 4) != "_Hid")
                {
                    this.drpd_tableName.Items.Add(new ListItem("i", _Table.Rows[i]["Table_Name"].ToString()));
                    if (_Table.Rows[i]["Table_Name"].ToString() == _rule.sheetName) 
                    {
                        this.drpd_tableName.SelectedIndex = i;
                    }
                }

            }    
            string[] ss1 = _rule.position.Split(new[] { "," }, StringSplitOptions.None);
            this.txtb_hang.Text = ss1[0].ToString();
            this.txtb_lei.Text = ss1[1].ToString();
            if (_rule.symbol == ">")
            {
                this.drpd_symbol.SelectedIndex = 0;
            }
           if (_rule.symbol == ">=")
           {
               this.drpd_symbol.SelectedIndex = 1;
           }
           if (_rule.symbol == "<")
           {
               this.drpd_symbol.SelectedIndex = 2;
           }
           if (_rule.symbol == "<=")
           {
               this.drpd_symbol.SelectedIndex = 3;
           }
           if (_rule.symbol == "=")
           {
               this.drpd_symbol.SelectedIndex = 4;
           }
           if (_rule.symbol == "!=")
           {
               this.drpd_symbol.SelectedIndex = 5;
           }
           if (_rule.symbol == "in")
           {
               this.drpd_symbol.SelectedIndex = 6;
           }
           if (_rule.symbol == "not in")
           {
               this.drpd_symbol.SelectedIndex = 7;
           }
           this.txtb_email.Text = _rule.email.Trim();
           this.txtb_aim.Text = _rule.aim;
           this.txtb_attachmentSource.Text = _rule.attachmentSource;
           this.txtb_article.Text = _rule.article;
           if (_rule.planType.Trim() == "once")
           {
               this.drpd_reportType.SelectedIndex = 0;
               this.dtme_OnceDateTime.Value = this._rule.startDate;
               DateTime d = DateTime.Now;
               d = tools.ClearHMS(d);
               d = d.AddHours(this._rule.startTime.Hours);
               d = d.AddMinutes(this._rule.startTime.Minutes);
               d = d.AddSeconds(this._rule.startTime.Seconds);
               this.dtme_OnceTime.Value = d;
               this.gbox_once.Enabled = true;
               this.gbox_more.Enabled = false;
               this.gbox_everydaymore.Enabled = false;
               this.gbox_moreCTime.Enabled = false;
           }
           else 
           {
               this.drpd_reportType.SelectedIndex = 1;
               if (_rule.timeType == "D") 
               {
                   this.drpd_pinlv.SelectedIndex = 0;
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
                   if (_rule.everydayFreSpace == null)
                   {
                       DateTime d = DateTime.Now;
                       d = tools.ClearHMS(d);
                       d = d.AddHours(this._rule.startTime.Hours);
                       d = d.AddMinutes(this._rule.startTime.Minutes);
                       d = d.AddSeconds(this._rule.startTime.Seconds);
                       this.dtme_MoreTime.Value = d;
                   }
                   else 
                   {
                       this.rbtn_mtpinlv_once.Checked = false;
                       this.dtme_MoreTime.Enabled = false;
                       this.rbtn_mtpinlv_more.Checked = true;
                       this.drpd_mtpinlv_time.Enabled = true;
                       this.drpd_timeType.Enabled = true;
                   }
                
               }
               if (_rule.timeType == "W") 
               {
                   this.drpd_pinlv.SelectedIndex = 1;
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
               if (_rule.timeType == "M") 
               {
                   this.drpd_pinlv.SelectedIndex = 2;
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
               this.dtme_cxTime_startTime.Value = _rule.startDate;
               DateTime nulltime=new DateTime();
               if (_rule.endDate != nulltime)
               {
                   this.dtme_cxTime_endTime.Value = _rule.endDate;
                   nulltime = DateTime.Now;
                   nulltime = nulltime.AddHours(this._rule.endDate.Hour);
                   nulltime = nulltime.AddMinutes(this._rule.startTime.Minutes);
                   nulltime = nulltime.AddSeconds(this._rule.startTime.Seconds);
                   this.dtme_lastEndTime.Value = nulltime;
               }
               else 
               {
                   this.rbtn_cxTime_noendTime.Checked = true;
                   this.rbtn_cxTime_endTime.Checked = false;
                   this.dtme_cxTime_endTime.Enabled = false;
                   this.dtme_lastEndTime.Enabled = false;
               }
           }
         
        }

        private void update_Load(object sender, EventArgs e)
        {
           
        }

        private void bton_search_Click(object sender, EventArgs e)
        {
            this.drpd_tableName.Items.Clear();
            OpenFileDialog ofd = new OpenFileDialog();
            ofd.ShowDialog();
            this.txtb_reportSource.Text = ofd.FileName;
            DataTable _Table = tools.GetExcelTableName(ofd.FileName);
            for (int i = 0; i != _Table.Rows.Count; i++)
            {
                if (_Table.Rows[i]["Table_Name"].ToString().Substring(0, 4) != "_Hid")
                {
                    this.drpd_tableName.Items.Add(new ListItem("i", _Table.Rows[i]["Table_Name"].ToString()));
                }

            }
        }

        private void bton_fileClear_Click(object sender, EventArgs e)
        {
            this.txtb_reportSource.Text = "";
            
        }

     

        private void bton_fileBName_Click(object sender, EventArgs e)
        {
           
            this.bton_search.Enabled = true;
            this.drpd_tableName.Enabled = true;
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

        private void rbtn_cxTime_noendTime_CheckedChanged(object sender, EventArgs e)
        {

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

        private void rbtn_cxTime_endTime_CheckedChanged(object sender, EventArgs e)
        {
            this.dtme_cxTime_endTime.Enabled = true;
            this.dtme_lastEndTime.Enabled = true;
        }

        private void rbtn_cxTime_noendTime_CheckedChanged_1(object sender, EventArgs e)
        {
            this.dtme_cxTime_endTime.Enabled = false;
            this.dtme_lastEndTime.Enabled = false;
        }
    }
}
