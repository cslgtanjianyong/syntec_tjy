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
    public partial class searchLog : Form
    {
        public searchLog()
        {
            InitializeComponent();
        }

        private void searchLog_Load(object sender, EventArgs e)
        {
            logCon logcon = new logCon();
            logcon.txtb_fileName.Text = "6月加班请假报表-总公司";
            logcon.txtb_startTime.Text = "2013-12-05 09:00:00.000";
            logcon.txtb_describe.Text = "成功发送给823486418@qq.com邮件";
            this.panl_log.Controls.Add(logcon);
        }
    }
}
