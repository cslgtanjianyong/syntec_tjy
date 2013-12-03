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
            if (!File.Exists("c:/Winform_ExcelWarning.xls"))
            {
                OleDbConnection cn = new OleDbConnection(tools.sConnectionString);
                string sqlCreate1 = "CREATE TABLE 目标用户信息表 ([用户姓名] VarChar,[用户邮箱地址] VarChar)";
                string sqlCreate2 = "CREATE TABLE 报表规则表 ([报表路径] VarChar,[报表栏位] VarChar,[具体规则] VarChar,[姓名] VarChar,[Email] VarChar)";
                OleDbCommand cmd1 = new OleDbCommand(sqlCreate1, cn);//创建Excel文件：
                OleDbCommand cmd2 = new OleDbCommand(sqlCreate2, cn);//创建Excel文件：
                cn.Open();
                cmd1.ExecuteNonQuery();
                cmd2.ExecuteNonQuery();
                cn.Close();
                return;
            }
        }

        private void bton_addPerson_Click(object sender, EventArgs e)
        {
            FormAddName formAddName = new FormAddName();
            formAddName.Show();
            this.Hide();
        }

        private void bton_addGuize_Click(object sender, EventArgs e)
        {
            FormAddReportforms formAddReportforms = new FormAddReportforms();
            formAddReportforms.Show();
            this.Hide();
        }
    }
}
