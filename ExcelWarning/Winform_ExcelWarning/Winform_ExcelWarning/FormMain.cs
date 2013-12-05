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
    public partial class FormMain : Form
    {
        public FormMain()
        {
            InitializeComponent();
        }

        private void FormMain_Load(object sender, EventArgs e)
        {
            //if (!File.Exists("c:/Winform_ExcelWarning.xls"))
            //{
            //    OleDbConnection cn = new OleDbConnection(tools.sConnectionString);
            //    string sqlCreate = "CREATE TABLE 报表规则表 ([报表路径] VarChar,[报表栏位] VarChar,[具体规则] VarChar,[姓名] VarChar,[Email] VarChar)";
            //    OleDbCommand cmd = new OleDbCommand(sqlCreate, cn);//创建Excel文件：
            //    cn.Open();
            //    cmd.ExecuteNonQuery();
            //    cn.Close();
            //    return;
            //}
            ruleCon rulecon = new ruleCon();
            rulecon.Left = 0;
            rulecon.Top = 0;
            rulecon.txtb_fileName.Text = "6月加班请假报表-总公司";
            rulecon.txtb_position.Text = "B4";
            rulecon.txtb_rule.Text = "大于10";
            rulecon.txtb_describe.Text = "只执行一次";
            rulecon.txtb_startTime.Text = "2013/12/5 9:20:00";
            rulecon.txtb_endTime.Text = "2013/12/5 9:20:00";
            rulecon.txtb_nextTime.Text = "2013/12/5 9:20:00";
            panl_rule.Controls.Add(rulecon);
            
            
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
            Console.WriteLine("请输入要提取的字符串：");
            string str = "10分钟";
            int number = 0; string num = null; 
            foreach (char item in str) 
            {
                if (item >= 48 && item <= 58) 
                {
                    num += item;
                } 
            }
            number = int.Parse(num);
            Console.WriteLine(number); 
        } 
    }
}
