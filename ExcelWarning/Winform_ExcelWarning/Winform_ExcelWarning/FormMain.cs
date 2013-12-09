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
using System.Threading;



namespace Winform_ExcelWarning
{
    public partial class FormMain : Form
    {
        public Thread thread;
        public FormMain()
        {
            InitializeComponent();
        }
        public void addFriend(string a)
        {

        }
        private void FormMain_Load(object sender, EventArgs e)
        {
            syntec.BLL.ExcelRule rulebll = new syntec.BLL.ExcelRule();
            List<syntec.Model.ExcelRule> rulelist = new List<syntec.Model.ExcelRule>();
            rulelist = rulebll.GetModelList("1=1");
            int i = 0;
            DateTime nulltime = new DateTime();
            foreach (syntec.Model.ExcelRule rule in rulelist)
            {
                ruleCon rulecon = new ruleCon();
                rulecon._rule = rule;
                rulecon.Left = 0;
                rulecon.Top = rulecon.Height * i;
                i++;
                rulecon.txtb_fileName.Text = rule.sheetName;
                rulecon.txtb_position.Text = rule.position;
                rulecon.txtb_rule.Text = rule.symbol + rule.aim;
                rulecon.txtb_describe.Text = tools.describe(rule);
                rulecon.txtb_startTime.Text = rule.startDate.ToString();
                if (rule.endDate == nulltime)
                {
                    rulecon.txtb_endTime.Text = "无结束日期";
                }
                else
                {
                    rulecon.txtb_endTime.Text = rule.endDate.ToString();
                }
                if (rule.nextDoTimeDate != null)
                {

                    rulecon.imag_state.Image = Image.FromFile(Application.StartupPath + "\\Resources\\true.jpg");
                }
                else
                {
                    rulecon.imag_state.Image = Image.FromFile(Application.StartupPath + "\\Resources\\false.jpg");
                }
                if (rule.nextDoTimeDate == null)
                {
                    rulecon.txtb_nextTime.Text = "已经无法继续执行了！！";
                }
                else
                {
                    rulecon.txtb_nextTime.Text = rule.nextDoTimeDate.ToString();
                }
                panl_rule.Controls.Add(rulecon);
            }
        }


        private void bton_addGuize_Click(object sender, EventArgs e)
        {
            FormAddReportforms formAddReportforms = new FormAddReportforms();
            formAddReportforms.Show();
            this.Hide();
        }

        private void bton_searchGuize_Click(object sender, EventArgs e)
        {
            MessageBox.Show(DateTime.Now.ToString());
        }

        private void bton_start_Click(object sender, EventArgs e)
        {
             ThreadExcel lis = new ThreadExcel(this);
             thread = new Thread(new ThreadStart(lis.start));
             thread.IsBackground = true;
             thread.Start();
             MessageBox.Show("检测已经开始");
             this.bton_start.Enabled = false;
             this.bton_end.Enabled = true;


           // syntec.BLL.ExcelRule ebll = new syntec.BLL.ExcelRule();
           // ebll.GetModelList("1=1");
           // foreach (syntec.Model.ExcelRule rule in ebll.GetModelList("1=1"))
           // {
           //    DateTime d=Convert.ToDateTime(tools.getnextToDateTime(rule,false));
           // }    
        }

        private void bton_end_Click(object sender, EventArgs e)
        {
            thread.Abort();
            this.bton_start.Enabled = true;
            this.bton_end.Enabled = false;
            MessageBox.Show("检测已经终止");
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void imag_logo_Click(object sender, EventArgs e)
        {

        }


    }
}
