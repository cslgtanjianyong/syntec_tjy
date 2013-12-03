namespace Winform_ExcelWarning
{
    partial class FormAddReportforms
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.bton_sure = new System.Windows.Forms.Button();
            this.bton_restart = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.drpd_personName = new System.Windows.Forms.ComboBox();
            this.txtb_reportguize = new System.Windows.Forms.TextBox();
            this.txtb_reportlanwei = new System.Windows.Forms.TextBox();
            this.txtb_reportSource = new System.Windows.Forms.TextBox();
            this.bton_search = new System.Windows.Forms.Button();
            this.bton_back = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // bton_sure
            // 
            this.bton_sure.Location = new System.Drawing.Point(28, 182);
            this.bton_sure.Name = "bton_sure";
            this.bton_sure.Size = new System.Drawing.Size(75, 23);
            this.bton_sure.TabIndex = 0;
            this.bton_sure.Text = "确定";
            this.bton_sure.UseVisualStyleBackColor = true;
            this.bton_sure.Click += new System.EventHandler(this.bton_sure_Click);
            // 
            // bton_restart
            // 
            this.bton_restart.Location = new System.Drawing.Point(138, 182);
            this.bton_restart.Name = "bton_restart";
            this.bton_restart.Size = new System.Drawing.Size(75, 23);
            this.bton_restart.TabIndex = 1;
            this.bton_restart.Text = "重填";
            this.bton_restart.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(50, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(65, 12);
            this.label1.TabIndex = 2;
            this.label1.Text = "选择报表：";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(50, 50);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(65, 12);
            this.label2.TabIndex = 3;
            this.label2.Text = "报表栏位：";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(50, 93);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(65, 12);
            this.label3.TabIndex = 4;
            this.label3.Text = "栏位规则：";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(26, 143);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(89, 12);
            this.label4.TabIndex = 5;
            this.label4.Text = "发给人员姓名：";
            // 
            // drpd_personName
            // 
            this.drpd_personName.FormattingEnabled = true;
            this.drpd_personName.Location = new System.Drawing.Point(121, 140);
            this.drpd_personName.Name = "drpd_personName";
            this.drpd_personName.Size = new System.Drawing.Size(127, 20);
            this.drpd_personName.TabIndex = 6;
            // 
            // txtb_reportguize
            // 
            this.txtb_reportguize.Location = new System.Drawing.Point(121, 90);
            this.txtb_reportguize.Name = "txtb_reportguize";
            this.txtb_reportguize.Size = new System.Drawing.Size(127, 21);
            this.txtb_reportguize.TabIndex = 7;
            // 
            // txtb_reportlanwei
            // 
            this.txtb_reportlanwei.Location = new System.Drawing.Point(121, 47);
            this.txtb_reportlanwei.Name = "txtb_reportlanwei";
            this.txtb_reportlanwei.Size = new System.Drawing.Size(127, 21);
            this.txtb_reportlanwei.TabIndex = 8;
            // 
            // txtb_reportSource
            // 
            this.txtb_reportSource.Location = new System.Drawing.Point(121, 6);
            this.txtb_reportSource.Name = "txtb_reportSource";
            this.txtb_reportSource.ReadOnly = true;
            this.txtb_reportSource.Size = new System.Drawing.Size(127, 21);
            this.txtb_reportSource.TabIndex = 9;
            // 
            // bton_search
            // 
            this.bton_search.Location = new System.Drawing.Point(254, 4);
            this.bton_search.Name = "bton_search";
            this.bton_search.Size = new System.Drawing.Size(55, 23);
            this.bton_search.TabIndex = 10;
            this.bton_search.Text = "浏览";
            this.bton_search.UseVisualStyleBackColor = true;
            this.bton_search.Click += new System.EventHandler(this.bton_search_Click);
            // 
            // bton_back
            // 
            this.bton_back.Location = new System.Drawing.Point(243, 182);
            this.bton_back.Name = "bton_back";
            this.bton_back.Size = new System.Drawing.Size(75, 23);
            this.bton_back.TabIndex = 11;
            this.bton_back.Text = "返回主界面";
            this.bton_back.UseVisualStyleBackColor = true;
            this.bton_back.Click += new System.EventHandler(this.bton_back_Click);
            // 
            // FormAddReportforms
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(347, 245);
            this.Controls.Add(this.bton_back);
            this.Controls.Add(this.bton_search);
            this.Controls.Add(this.txtb_reportSource);
            this.Controls.Add(this.txtb_reportlanwei);
            this.Controls.Add(this.txtb_reportguize);
            this.Controls.Add(this.drpd_personName);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.bton_restart);
            this.Controls.Add(this.bton_sure);
            this.Name = "FormAddReportforms";
            this.Text = "FormAddReportforms";
            this.Load += new System.EventHandler(this.FormAddReportforms_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button bton_sure;
        private System.Windows.Forms.Button bton_restart;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.ComboBox drpd_personName;
        private System.Windows.Forms.TextBox txtb_reportguize;
        private System.Windows.Forms.TextBox txtb_reportlanwei;
        private System.Windows.Forms.TextBox txtb_reportSource;
        private System.Windows.Forms.Button bton_search;
        private System.Windows.Forms.Button bton_back;

    }
}