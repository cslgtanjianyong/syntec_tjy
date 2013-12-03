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
    public partial class FormAddName : Form
    {
        public FormAddName()
        {
            InitializeComponent();
        }

        private void bton_sure_Click(object sender, EventArgs e)
        {
            if (this.txtb_userEmail.Text.Trim().Length  == 0 || this.txtb_userEmail.Text.Trim().Length == 0) 
            {
                MessageBox.Show("请输入完整用户名和用户邮箱！！！！");
                return;
            }
            if (!tools.HasEmail(this.txtb_userEmail.Text.Trim())) 
            {
                MessageBox.Show("不符合邮箱格式请重试！！！！请重新输入");
                this.txtb_userEmail.Text = "";
                return;
            }
            
                    
                    OleDbConnection cn = new OleDbConnection(tools.sConnectionString);
                    cn.Open();//创建TestSheet工作表
                    string sqlInsert = "INSERT INTO 目标用户信息表 VALUES(" + "'" + this.txtb_userName.Text.Trim() + "'," + "'" + this.txtb_userEmail.Text.Trim() + "'" + ")";
                    OleDbCommand myCommandd = new OleDbCommand(sqlInsert, cn);
                    myCommandd.ExecuteNonQuery();
                    cn.Close();
                
            
           // catch (Exception ex) { MessageBox.Show("c:/Winform_ExcelWarning.xls处于打开状态，请关闭后重试"); }
        }

        private void bton_restart_Click(object sender, EventArgs e)
        {
            this.txtb_userEmail.Text = "";
            this.txtb_userName.Text = "";
        }

        private void bton_continue_Click(object sender, EventArgs e)
        {
            this.txtb_userEmail.Text = "";
            this.txtb_userName.Text = "";
        }

        private void bton_back_Click(object sender, EventArgs e)
        {
            FormMain formMain = new FormMain();
            formMain.Show();
            this.Hide();
        }

    

       
    }
}
