namespace Winform_ExcelWarning
{
    partial class FormAddName
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
            this.lbel_userName = new System.Windows.Forms.Label();
            this.lbel_userEmail = new System.Windows.Forms.Label();
            this.txtb_userName = new System.Windows.Forms.TextBox();
            this.txtb_userEmail = new System.Windows.Forms.TextBox();
            this.bton_sure = new System.Windows.Forms.Button();
            this.bton_restart = new System.Windows.Forms.Button();
            this.bton_continue = new System.Windows.Forms.Button();
            this.bton_back = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // lbel_userName
            // 
            this.lbel_userName.AutoSize = true;
            this.lbel_userName.Location = new System.Drawing.Point(38, 58);
            this.lbel_userName.Name = "lbel_userName";
            this.lbel_userName.Size = new System.Drawing.Size(89, 12);
            this.lbel_userName.TabIndex = 0;
            this.lbel_userName.Text = "目标用户姓名：";
            // 
            // lbel_userEmail
            // 
            this.lbel_userEmail.AutoSize = true;
            this.lbel_userEmail.Location = new System.Drawing.Point(38, 117);
            this.lbel_userEmail.Name = "lbel_userEmail";
            this.lbel_userEmail.Size = new System.Drawing.Size(89, 12);
            this.lbel_userEmail.TabIndex = 1;
            this.lbel_userEmail.Text = "目标用户邮箱：";
            // 
            // txtb_userName
            // 
            this.txtb_userName.Location = new System.Drawing.Point(121, 55);
            this.txtb_userName.Name = "txtb_userName";
            this.txtb_userName.Size = new System.Drawing.Size(193, 21);
            this.txtb_userName.TabIndex = 2;
            // 
            // txtb_userEmail
            // 
            this.txtb_userEmail.Location = new System.Drawing.Point(121, 114);
            this.txtb_userEmail.Name = "txtb_userEmail";
            this.txtb_userEmail.Size = new System.Drawing.Size(193, 21);
            this.txtb_userEmail.TabIndex = 3;
            // 
            // bton_sure
            // 
            this.bton_sure.Location = new System.Drawing.Point(40, 165);
            this.bton_sure.Name = "bton_sure";
            this.bton_sure.Size = new System.Drawing.Size(47, 23);
            this.bton_sure.TabIndex = 4;
            this.bton_sure.Text = "确定";
            this.bton_sure.UseVisualStyleBackColor = true;
            this.bton_sure.Click += new System.EventHandler(this.bton_sure_Click);
            // 
            // bton_restart
            // 
            this.bton_restart.Location = new System.Drawing.Point(121, 165);
            this.bton_restart.Name = "bton_restart";
            this.bton_restart.Size = new System.Drawing.Size(46, 23);
            this.bton_restart.TabIndex = 5;
            this.bton_restart.Text = "重填";
            this.bton_restart.UseVisualStyleBackColor = true;
            this.bton_restart.Click += new System.EventHandler(this.bton_restart_Click);
            // 
            // bton_continue
            // 
            this.bton_continue.Location = new System.Drawing.Point(187, 164);
            this.bton_continue.Name = "bton_continue";
            this.bton_continue.Size = new System.Drawing.Size(66, 23);
            this.bton_continue.TabIndex = 6;
            this.bton_continue.Text = "继续填写";
            this.bton_continue.UseVisualStyleBackColor = true;
            this.bton_continue.Click += new System.EventHandler(this.bton_continue_Click);
            // 
            // bton_back
            // 
            this.bton_back.Location = new System.Drawing.Point(273, 164);
            this.bton_back.Name = "bton_back";
            this.bton_back.Size = new System.Drawing.Size(75, 23);
            this.bton_back.TabIndex = 7;
            this.bton_back.Text = "返回主界面";
            this.bton_back.UseVisualStyleBackColor = true;
            this.bton_back.Click += new System.EventHandler(this.bton_back_Click);
            // 
            // FormAddName
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(360, 316);
            this.Controls.Add(this.bton_back);
            this.Controls.Add(this.bton_continue);
            this.Controls.Add(this.bton_restart);
            this.Controls.Add(this.bton_sure);
            this.Controls.Add(this.txtb_userEmail);
            this.Controls.Add(this.txtb_userName);
            this.Controls.Add(this.lbel_userEmail);
            this.Controls.Add(this.lbel_userName);
            this.Name = "FormAddName";
            this.Text = "添加目标用户";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbel_userName;
        private System.Windows.Forms.Label lbel_userEmail;
        private System.Windows.Forms.TextBox txtb_userName;
        private System.Windows.Forms.TextBox txtb_userEmail;
        private System.Windows.Forms.Button bton_sure;
        private System.Windows.Forms.Button bton_restart;
        private System.Windows.Forms.Button bton_continue;
        private System.Windows.Forms.Button bton_back;
    }
}

