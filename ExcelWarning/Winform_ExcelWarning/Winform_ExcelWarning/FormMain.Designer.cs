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
            this.bton_addGuize = new System.Windows.Forms.Button();
            this.bton_searchGuize = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // bton_addGuize
            // 
            this.bton_addGuize.Location = new System.Drawing.Point(71, 162);
            this.bton_addGuize.Name = "bton_addGuize";
            this.bton_addGuize.Size = new System.Drawing.Size(105, 23);
            this.bton_addGuize.TabIndex = 1;
            this.bton_addGuize.Text = "添加EXCEL规则";
            this.bton_addGuize.UseVisualStyleBackColor = true;
            this.bton_addGuize.Click += new System.EventHandler(this.bton_addGuize_Click);
            // 
            // bton_searchGuize
            // 
            this.bton_searchGuize.Location = new System.Drawing.Point(276, 162);
            this.bton_searchGuize.Name = "bton_searchGuize";
            this.bton_searchGuize.Size = new System.Drawing.Size(132, 23);
            this.bton_searchGuize.TabIndex = 2;
            this.bton_searchGuize.Text = "查看EXCEL规则情况";
            this.bton_searchGuize.UseVisualStyleBackColor = true;
            this.bton_searchGuize.Click += new System.EventHandler(this.bton_searchGuize_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::Winform_ExcelWarning.Properties.Resources.Syntec;
            this.pictureBox1.Location = new System.Drawing.Point(71, 32);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(337, 99);
            this.pictureBox1.TabIndex = 3;
            this.pictureBox1.TabStop = false;
            // 
            // FormMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(472, 278);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.bton_searchGuize);
            this.Controls.Add(this.bton_addGuize);
            this.Name = "FormMain";
            this.Text = "Excel报警器";
            this.Load += new System.EventHandler(this.FormMain_Load);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button bton_addGuize;
        private System.Windows.Forms.Button bton_searchGuize;
        private System.Windows.Forms.PictureBox pictureBox1;
    }
}