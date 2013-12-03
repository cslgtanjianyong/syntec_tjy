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

        private void bton_sure_Click(object sender, EventArgs e)
        {
            string email = "" ;
            if (this.txtb_reportguize.Text.Trim().Length == 0 || this.txtb_reportlanwei.Text.Trim().Length==0||this.txtb_reportSource.Text.Trim().Length==0||this.drpd_personName.SelectedItem.ToString()==null)
            {
                MessageBox.Show("请完整信息内容！！！！");
                return;
            }
                 
                    OleDbConnection cn = new OleDbConnection(tools.sConnectionString);
                    cn.Open();//创建TestSheet工作表
                    foreach (Person p in tools.persons) 
                    {
                        if (p.Name == this.drpd_personName.SelectedItem.ToString()) 
                        {
                            email = p.Email;
                        }
                    }
                    string sqlInsert = "INSERT INTO 报表规则表 VALUES(" + "'" + this.txtb_reportSource.Text.Trim() + "'," + "'" + this.txtb_reportlanwei.Text.Trim() + "'" + "," + "'" + this.txtb_reportguize.Text.Trim() + "'" + "," + "'" + this.drpd_personName.SelectedItem.ToString() + "'" + "," + "'" + email + "'" + ")";
                    OleDbCommand myCommandd = new OleDbCommand(sqlInsert, cn);
                    myCommandd.ExecuteNonQuery();
                    cn.Close();               
        }

        private void FormAddReportforms_Load(object sender, EventArgs e)
        {
            this.drpd_personName.Items.Clear();
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
            foreach(DataRow dr in dt.Rows){  //遍历列             
                    Person person = new Person();
                    person.Name = dr[0].ToString();
                    person.Email = dr[1].ToString();
                    tools.persons.Add(person);               
              }
          //  MessageBox.Show(tools.persons[0].Email);
            foreach (Person p in tools.persons)
            {
                this.drpd_personName.Items.Add(new ListItem("Name", p.Name));
            }
        }

        private void bton_back_Click(object sender, EventArgs e)
        {
            FormMain formMain = new FormMain();
            formMain.Show();
            this.Hide();
        }

     

      
    }
}

