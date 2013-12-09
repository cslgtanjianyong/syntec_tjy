using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Winform_ExcelWarning
{
    public partial class ruleCon : UserControl
    {
        public syntec.Model.ExcelRule _rule;
        public ruleCon()
        {
            InitializeComponent();
        }

        private void txtb_position_TextChanged(object sender, EventArgs e)
        {

        }

        private void bton_update_Click(object sender, EventArgs e)
        {
            update l = new update(_rule);
            l.Show();
        }
    }
}
