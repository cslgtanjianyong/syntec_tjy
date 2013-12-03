namespace Winform_ExcelWarning
{
    partial class FormMain
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
            this.bton_addPerson = new System.Windows.Forms.Button();
            this.bton_addGuize = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // bton_addPerson
            // 
            this.bton_addPerson.Location = new System.Drawing.Point(31, 40);
            this.bton_addPerson.Name = "bton_addPerson";
            this.bton_addPerson.Size = new System.Drawing.Size(99, 23);
            this.bton_addPerson.TabIndex = 0;
            this.bton_addPerson.Text = "添加对象资料";
            this.bton_addPerson.UseVisualStyleBackColor = true;
            this.bton_addPerson.Click += new System.EventHandler(this.bton_addPerson_Click);
            // 
            // bton_addGuize
            // 
            this.bton_addGuize.Location = new System.Drawing.Point(31, 124);
            this.bton_addGuize.Name = "bton_addGuize";
            this.bton_addGuize.Size = new System.Drawing.Size(99, 23);
            this.bton_addGuize.TabIndex = 1;
            this.bton_addGuize.Text = "添加EXCEL规则";
            this.bton_addGuize.UseVisualStyleBackColor = true;
            this.bton_addGuize.Click += new System.EventHandler(this.bton_addGuize_Click);
            // 
            // FormMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 262);
            this.Controls.Add(this.bton_addGuize);
            this.Controls.Add(this.bton_addPerson);
            this.Name = "FormMain";
            this.Text = "主页面";
            this.Load += new System.EventHandler(this.FormMain_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button bton_addPerson;
        private System.Windows.Forms.Button bton_addGuize;
    }
}