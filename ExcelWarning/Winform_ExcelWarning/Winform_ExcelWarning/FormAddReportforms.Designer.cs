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
            this.lbel_reportSource = new System.Windows.Forms.Label();
            this.lbel_position = new System.Windows.Forms.Label();
            this.lbel_symbol = new System.Windows.Forms.Label();
            this.lbel_email = new System.Windows.Forms.Label();
            this.txtb_hang = new System.Windows.Forms.TextBox();
            this.txtb_reportSource = new System.Windows.Forms.TextBox();
            this.bton_search = new System.Windows.Forms.Button();
            this.bton_back = new System.Windows.Forms.Button();
            this.drpd_symbol = new System.Windows.Forms.ComboBox();
            this.lbel_planType = new System.Windows.Forms.Label();
            this.drpd_reportType = new System.Windows.Forms.ComboBox();
            this.gbox_once = new System.Windows.Forms.GroupBox();
            this.lbel_oncetime = new System.Windows.Forms.Label();
            this.dtme_OnceTime = new System.Windows.Forms.DateTimePicker();
            this.dtme_OnceDateTime = new System.Windows.Forms.DateTimePicker();
            this.lbel_oncestartDate = new System.Windows.Forms.Label();
            this.gbox_more = new System.Windows.Forms.GroupBox();
            this.lbel_pinlv_month = new System.Windows.Forms.Label();
            this.drpd_pinlv_month = new System.Windows.Forms.NumericUpDown();
            this.lbel_pinlv_mei = new System.Windows.Forms.Label();
            this.cbox_pinlv_Sunday = new System.Windows.Forms.CheckBox();
            this.cbox_pinlv_Saturday = new System.Windows.Forms.CheckBox();
            this.cbox_pinlv_Friday = new System.Windows.Forms.CheckBox();
            this.cbox_pinlv_Thursday = new System.Windows.Forms.CheckBox();
            this.cbox_pinlv_Wednesday = new System.Windows.Forms.CheckBox();
            this.cbox_pinlv_Tuesday = new System.Windows.Forms.CheckBox();
            this.cbox_pinlv_Firstday = new System.Windows.Forms.CheckBox();
            this.lbel_pinlv_week = new System.Windows.Forms.Label();
            this.drpd_pinlv_day = new System.Windows.Forms.NumericUpDown();
            this.drpd_pinlv_week = new System.Windows.Forms.NumericUpDown();
            this.lbel_pinlv_day = new System.Windows.Forms.Label();
            this.lbel_pinlvSpace = new System.Windows.Forms.Label();
            this.drpd_pinlv = new System.Windows.Forms.ComboBox();
            this.lbel_actType = new System.Windows.Forms.Label();
            this.gbox_everydaymore = new System.Windows.Forms.GroupBox();
            this.dtme_MoreTime = new System.Windows.Forms.DateTimePicker();
            this.drpd_mtpinlv_time = new System.Windows.Forms.NumericUpDown();
            this.drpd_timeType = new System.Windows.Forms.ComboBox();
            this.rbtn_mtpinlv_more = new System.Windows.Forms.RadioButton();
            this.rbtn_mtpinlv_once = new System.Windows.Forms.RadioButton();
            this.gbox_moreCTime = new System.Windows.Forms.GroupBox();
            this.dtme_cxTime_endTime = new System.Windows.Forms.DateTimePicker();
            this.rbtn_cxTime_noendTime = new System.Windows.Forms.RadioButton();
            this.rbtn_cxTime_endTime = new System.Windows.Forms.RadioButton();
            this.dtme_cxTime_startTime = new System.Windows.Forms.DateTimePicker();
            this.txtb_morestartTime = new System.Windows.Forms.Label();
            this.lbel_content = new System.Windows.Forms.Label();
            this.txtb_aim = new System.Windows.Forms.TextBox();
            this.lbel_hang = new System.Windows.Forms.Label();
            this.txtb_lei = new System.Windows.Forms.TextBox();
            this.lbel_lei = new System.Windows.Forms.Label();
            this.txtb_email = new System.Windows.Forms.TextBox();
            this.lbel_article = new System.Windows.Forms.Label();
            this.txtb_article = new System.Windows.Forms.TextBox();
            this.lbel_attachment = new System.Windows.Forms.Label();
            this.txtb_attachmentSource = new System.Windows.Forms.TextBox();
            this.bton_searchAttachment = new System.Windows.Forms.Button();
            this.lbel_tableName = new System.Windows.Forms.Label();
            this.drpd_tableName = new System.Windows.Forms.ComboBox();
            this.lbel_fileBName = new System.Windows.Forms.Label();
            this.bton_filesearch = new System.Windows.Forms.Button();
            this.txtb_fileBName = new System.Windows.Forms.TextBox();
            this.folderBrowserDialog1 = new System.Windows.Forms.FolderBrowserDialog();
            this.bton_fileClear = new System.Windows.Forms.Button();
            this.bton_fileBName = new System.Windows.Forms.Button();
            this.dtme_lastStartTime = new System.Windows.Forms.DateTimePicker();
            this.dtme_lastEndTime = new System.Windows.Forms.DateTimePicker();
            this.gbox_once.SuspendLayout();
            this.gbox_more.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.drpd_pinlv_month)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.drpd_pinlv_day)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.drpd_pinlv_week)).BeginInit();
            this.gbox_everydaymore.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.drpd_mtpinlv_time)).BeginInit();
            this.gbox_moreCTime.SuspendLayout();
            this.SuspendLayout();
            // 
            // bton_sure
            // 
            this.bton_sure.Location = new System.Drawing.Point(43, 586);
            this.bton_sure.Name = "bton_sure";
            this.bton_sure.Size = new System.Drawing.Size(87, 23);
            this.bton_sure.TabIndex = 0;
            this.bton_sure.Text = " 确定设置";
            this.bton_sure.UseVisualStyleBackColor = true;
            this.bton_sure.Click += new System.EventHandler(this.bton_sure_Click);
            // 
            // lbel_reportSource
            // 
            this.lbel_reportSource.AutoSize = true;
            this.lbel_reportSource.Location = new System.Drawing.Point(65, 45);
            this.lbel_reportSource.Name = "lbel_reportSource";
            this.lbel_reportSource.Size = new System.Drawing.Size(65, 12);
            this.lbel_reportSource.TabIndex = 2;
            this.lbel_reportSource.Text = "选择报表：";
            // 
            // lbel_position
            // 
            this.lbel_position.AutoSize = true;
            this.lbel_position.Location = new System.Drawing.Point(484, 45);
            this.lbel_position.Name = "lbel_position";
            this.lbel_position.Size = new System.Drawing.Size(65, 12);
            this.lbel_position.TabIndex = 3;
            this.lbel_position.Text = "报表栏位：";
            this.lbel_position.Click += new System.EventHandler(this.lbel_position_Click);
            // 
            // lbel_symbol
            // 
            this.lbel_symbol.AutoSize = true;
            this.lbel_symbol.Location = new System.Drawing.Point(67, 78);
            this.lbel_symbol.Name = "lbel_symbol";
            this.lbel_symbol.Size = new System.Drawing.Size(65, 12);
            this.lbel_symbol.TabIndex = 4;
            this.lbel_symbol.Text = "规则符号：";
            // 
            // lbel_email
            // 
            this.lbel_email.AutoSize = true;
            this.lbel_email.Location = new System.Drawing.Point(43, 107);
            this.lbel_email.Name = "lbel_email";
            this.lbel_email.Size = new System.Drawing.Size(89, 12);
            this.lbel_email.TabIndex = 5;
            this.lbel_email.Text = "发给人员邮箱：";
            // 
            // txtb_hang
            // 
            this.txtb_hang.Location = new System.Drawing.Point(550, 40);
            this.txtb_hang.Name = "txtb_hang";
            this.txtb_hang.Size = new System.Drawing.Size(33, 21);
            this.txtb_hang.TabIndex = 8;
            // 
            // txtb_reportSource
            // 
            this.txtb_reportSource.Location = new System.Drawing.Point(136, 42);
            this.txtb_reportSource.Name = "txtb_reportSource";
            this.txtb_reportSource.ReadOnly = true;
            this.txtb_reportSource.Size = new System.Drawing.Size(63, 21);
            this.txtb_reportSource.TabIndex = 9;
            // 
            // bton_search
            // 
            this.bton_search.Location = new System.Drawing.Point(208, 40);
            this.bton_search.Name = "bton_search";
            this.bton_search.Size = new System.Drawing.Size(55, 23);
            this.bton_search.TabIndex = 10;
            this.bton_search.Text = "浏览";
            this.bton_search.UseVisualStyleBackColor = true;
            this.bton_search.Click += new System.EventHandler(this.bton_search_Click);
            // 
            // bton_back
            // 
            this.bton_back.Location = new System.Drawing.Point(672, 1);
            this.bton_back.Name = "bton_back";
            this.bton_back.Size = new System.Drawing.Size(75, 23);
            this.bton_back.TabIndex = 11;
            this.bton_back.Text = "返回主界面";
            this.bton_back.UseVisualStyleBackColor = true;
            this.bton_back.Click += new System.EventHandler(this.bton_back_Click);
            // 
            // drpd_symbol
            // 
            this.drpd_symbol.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.drpd_symbol.FormattingEnabled = true;
            this.drpd_symbol.Location = new System.Drawing.Point(136, 75);
            this.drpd_symbol.Name = "drpd_symbol";
            this.drpd_symbol.Size = new System.Drawing.Size(127, 20);
            this.drpd_symbol.TabIndex = 12;
            this.drpd_symbol.SelectedIndexChanged += new System.EventHandler(this.drpd_symbol_SelectedIndexChanged);
            // 
            // lbel_planType
            // 
            this.lbel_planType.AutoSize = true;
            this.lbel_planType.Location = new System.Drawing.Point(65, 179);
            this.lbel_planType.Name = "lbel_planType";
            this.lbel_planType.Size = new System.Drawing.Size(65, 12);
            this.lbel_planType.TabIndex = 14;
            this.lbel_planType.Text = "计划类型：";
            // 
            // drpd_reportType
            // 
            this.drpd_reportType.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.drpd_reportType.FormattingEnabled = true;
            this.drpd_reportType.Location = new System.Drawing.Point(136, 176);
            this.drpd_reportType.Name = "drpd_reportType";
            this.drpd_reportType.Size = new System.Drawing.Size(127, 20);
            this.drpd_reportType.TabIndex = 15;
            this.drpd_reportType.SelectedIndexChanged += new System.EventHandler(this.drpd_reportType_SelectedIndexChanged);
            // 
            // gbox_once
            // 
            this.gbox_once.Controls.Add(this.lbel_oncetime);
            this.gbox_once.Controls.Add(this.dtme_OnceTime);
            this.gbox_once.Controls.Add(this.dtme_OnceDateTime);
            this.gbox_once.Controls.Add(this.lbel_oncestartDate);
            this.gbox_once.Location = new System.Drawing.Point(45, 202);
            this.gbox_once.Name = "gbox_once";
            this.gbox_once.Size = new System.Drawing.Size(633, 67);
            this.gbox_once.TabIndex = 16;
            this.gbox_once.TabStop = false;
            this.gbox_once.Text = "执行一次";
            // 
            // lbel_oncetime
            // 
            this.lbel_oncetime.AutoSize = true;
            this.lbel_oncetime.Location = new System.Drawing.Point(277, 27);
            this.lbel_oncetime.Name = "lbel_oncetime";
            this.lbel_oncetime.Size = new System.Drawing.Size(41, 12);
            this.lbel_oncetime.TabIndex = 3;
            this.lbel_oncetime.Text = "时间：";
            // 
            // dtme_OnceTime
            // 
            this.dtme_OnceTime.Format = System.Windows.Forms.DateTimePickerFormat.Time;
            this.dtme_OnceTime.Location = new System.Drawing.Point(336, 21);
            this.dtme_OnceTime.Name = "dtme_OnceTime";
            this.dtme_OnceTime.ShowUpDown = true;
            this.dtme_OnceTime.Size = new System.Drawing.Size(98, 21);
            this.dtme_OnceTime.TabIndex = 2;
            // 
            // dtme_OnceDateTime
            // 
            this.dtme_OnceDateTime.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dtme_OnceDateTime.Location = new System.Drawing.Point(93, 21);
            this.dtme_OnceDateTime.Name = "dtme_OnceDateTime";
            this.dtme_OnceDateTime.Size = new System.Drawing.Size(127, 21);
            this.dtme_OnceDateTime.TabIndex = 1;
            // 
            // lbel_oncestartDate
            // 
            this.lbel_oncestartDate.AutoSize = true;
            this.lbel_oncestartDate.Location = new System.Drawing.Point(22, 27);
            this.lbel_oncestartDate.Name = "lbel_oncestartDate";
            this.lbel_oncestartDate.Size = new System.Drawing.Size(65, 12);
            this.lbel_oncestartDate.TabIndex = 0;
            this.lbel_oncestartDate.Text = "执行日期：";
            // 
            // gbox_more
            // 
            this.gbox_more.Controls.Add(this.lbel_pinlv_month);
            this.gbox_more.Controls.Add(this.drpd_pinlv_month);
            this.gbox_more.Controls.Add(this.lbel_pinlv_mei);
            this.gbox_more.Controls.Add(this.cbox_pinlv_Sunday);
            this.gbox_more.Controls.Add(this.cbox_pinlv_Saturday);
            this.gbox_more.Controls.Add(this.cbox_pinlv_Friday);
            this.gbox_more.Controls.Add(this.cbox_pinlv_Thursday);
            this.gbox_more.Controls.Add(this.cbox_pinlv_Wednesday);
            this.gbox_more.Controls.Add(this.cbox_pinlv_Tuesday);
            this.gbox_more.Controls.Add(this.cbox_pinlv_Firstday);
            this.gbox_more.Controls.Add(this.lbel_pinlv_week);
            this.gbox_more.Controls.Add(this.drpd_pinlv_day);
            this.gbox_more.Controls.Add(this.drpd_pinlv_week);
            this.gbox_more.Controls.Add(this.lbel_pinlv_day);
            this.gbox_more.Controls.Add(this.lbel_pinlvSpace);
            this.gbox_more.Controls.Add(this.drpd_pinlv);
            this.gbox_more.Controls.Add(this.lbel_actType);
            this.gbox_more.Location = new System.Drawing.Point(45, 286);
            this.gbox_more.Name = "gbox_more";
            this.gbox_more.Size = new System.Drawing.Size(632, 80);
            this.gbox_more.TabIndex = 17;
            this.gbox_more.TabStop = false;
            this.gbox_more.Text = "频率";
            // 
            // lbel_pinlv_month
            // 
            this.lbel_pinlv_month.AutoSize = true;
            this.lbel_pinlv_month.Location = new System.Drawing.Point(257, 43);
            this.lbel_pinlv_month.Name = "lbel_pinlv_month";
            this.lbel_pinlv_month.Size = new System.Drawing.Size(29, 12);
            this.lbel_pinlv_month.TabIndex = 35;
            this.lbel_pinlv_month.Text = "个月";
            this.lbel_pinlv_month.Visible = false;
            // 
            // drpd_pinlv_month
            // 
            this.drpd_pinlv_month.Location = new System.Drawing.Point(195, 41);
            this.drpd_pinlv_month.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.drpd_pinlv_month.Name = "drpd_pinlv_month";
            this.drpd_pinlv_month.Size = new System.Drawing.Size(61, 21);
            this.drpd_pinlv_month.TabIndex = 34;
            this.drpd_pinlv_month.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.drpd_pinlv_month.Visible = false;
            // 
            // lbel_pinlv_mei
            // 
            this.lbel_pinlv_mei.AutoSize = true;
            this.lbel_pinlv_mei.Location = new System.Drawing.Point(177, 43);
            this.lbel_pinlv_mei.Name = "lbel_pinlv_mei";
            this.lbel_pinlv_mei.Size = new System.Drawing.Size(17, 12);
            this.lbel_pinlv_mei.TabIndex = 33;
            this.lbel_pinlv_mei.Text = "每";
            this.lbel_pinlv_mei.Visible = false;
            // 
            // cbox_pinlv_Sunday
            // 
            this.cbox_pinlv_Sunday.AutoSize = true;
            this.cbox_pinlv_Sunday.Location = new System.Drawing.Point(420, 46);
            this.cbox_pinlv_Sunday.Name = "cbox_pinlv_Sunday";
            this.cbox_pinlv_Sunday.Size = new System.Drawing.Size(60, 16);
            this.cbox_pinlv_Sunday.TabIndex = 32;
            this.cbox_pinlv_Sunday.Text = "星期天";
            this.cbox_pinlv_Sunday.UseVisualStyleBackColor = true;
            this.cbox_pinlv_Sunday.Visible = false;
            // 
            // cbox_pinlv_Saturday
            // 
            this.cbox_pinlv_Saturday.AutoSize = true;
            this.cbox_pinlv_Saturday.Location = new System.Drawing.Point(354, 46);
            this.cbox_pinlv_Saturday.Name = "cbox_pinlv_Saturday";
            this.cbox_pinlv_Saturday.Size = new System.Drawing.Size(60, 16);
            this.cbox_pinlv_Saturday.TabIndex = 31;
            this.cbox_pinlv_Saturday.Text = "星期六";
            this.cbox_pinlv_Saturday.UseVisualStyleBackColor = true;
            this.cbox_pinlv_Saturday.Visible = false;
            // 
            // cbox_pinlv_Friday
            // 
            this.cbox_pinlv_Friday.AutoSize = true;
            this.cbox_pinlv_Friday.Location = new System.Drawing.Point(288, 46);
            this.cbox_pinlv_Friday.Name = "cbox_pinlv_Friday";
            this.cbox_pinlv_Friday.Size = new System.Drawing.Size(60, 16);
            this.cbox_pinlv_Friday.TabIndex = 30;
            this.cbox_pinlv_Friday.Text = "星期五";
            this.cbox_pinlv_Friday.UseVisualStyleBackColor = true;
            this.cbox_pinlv_Friday.Visible = false;
            // 
            // cbox_pinlv_Thursday
            // 
            this.cbox_pinlv_Thursday.AutoSize = true;
            this.cbox_pinlv_Thursday.Location = new System.Drawing.Point(483, 20);
            this.cbox_pinlv_Thursday.Name = "cbox_pinlv_Thursday";
            this.cbox_pinlv_Thursday.Size = new System.Drawing.Size(60, 16);
            this.cbox_pinlv_Thursday.TabIndex = 29;
            this.cbox_pinlv_Thursday.Text = "星期四";
            this.cbox_pinlv_Thursday.UseVisualStyleBackColor = true;
            this.cbox_pinlv_Thursday.Visible = false;
            // 
            // cbox_pinlv_Wednesday
            // 
            this.cbox_pinlv_Wednesday.AutoSize = true;
            this.cbox_pinlv_Wednesday.Location = new System.Drawing.Point(420, 20);
            this.cbox_pinlv_Wednesday.Name = "cbox_pinlv_Wednesday";
            this.cbox_pinlv_Wednesday.Size = new System.Drawing.Size(60, 16);
            this.cbox_pinlv_Wednesday.TabIndex = 28;
            this.cbox_pinlv_Wednesday.Text = "星期三";
            this.cbox_pinlv_Wednesday.UseVisualStyleBackColor = true;
            this.cbox_pinlv_Wednesday.Visible = false;
            // 
            // cbox_pinlv_Tuesday
            // 
            this.cbox_pinlv_Tuesday.AutoSize = true;
            this.cbox_pinlv_Tuesday.Location = new System.Drawing.Point(354, 20);
            this.cbox_pinlv_Tuesday.Name = "cbox_pinlv_Tuesday";
            this.cbox_pinlv_Tuesday.Size = new System.Drawing.Size(60, 16);
            this.cbox_pinlv_Tuesday.TabIndex = 27;
            this.cbox_pinlv_Tuesday.Text = "星期二";
            this.cbox_pinlv_Tuesday.UseVisualStyleBackColor = true;
            this.cbox_pinlv_Tuesday.Visible = false;
            // 
            // cbox_pinlv_Firstday
            // 
            this.cbox_pinlv_Firstday.AutoSize = true;
            this.cbox_pinlv_Firstday.Location = new System.Drawing.Point(288, 20);
            this.cbox_pinlv_Firstday.Name = "cbox_pinlv_Firstday";
            this.cbox_pinlv_Firstday.Size = new System.Drawing.Size(60, 16);
            this.cbox_pinlv_Firstday.TabIndex = 26;
            this.cbox_pinlv_Firstday.Text = "星期一";
            this.cbox_pinlv_Firstday.UseVisualStyleBackColor = true;
            this.cbox_pinlv_Firstday.Visible = false;
            // 
            // lbel_pinlv_week
            // 
            this.lbel_pinlv_week.AutoSize = true;
            this.lbel_pinlv_week.Location = new System.Drawing.Point(160, 43);
            this.lbel_pinlv_week.Name = "lbel_pinlv_week";
            this.lbel_pinlv_week.Size = new System.Drawing.Size(17, 12);
            this.lbel_pinlv_week.TabIndex = 25;
            this.lbel_pinlv_week.Text = "周";
            this.lbel_pinlv_week.Visible = false;
            // 
            // drpd_pinlv_day
            // 
            this.drpd_pinlv_day.Location = new System.Drawing.Point(93, 41);
            this.drpd_pinlv_day.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.drpd_pinlv_day.Name = "drpd_pinlv_day";
            this.drpd_pinlv_day.Size = new System.Drawing.Size(61, 21);
            this.drpd_pinlv_day.TabIndex = 24;
            this.drpd_pinlv_day.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // drpd_pinlv_week
            // 
            this.drpd_pinlv_week.Location = new System.Drawing.Point(91, 41);
            this.drpd_pinlv_week.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.drpd_pinlv_week.Name = "drpd_pinlv_week";
            this.drpd_pinlv_week.Size = new System.Drawing.Size(61, 21);
            this.drpd_pinlv_week.TabIndex = 23;
            this.drpd_pinlv_week.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // lbel_pinlv_day
            // 
            this.lbel_pinlv_day.AutoSize = true;
            this.lbel_pinlv_day.Location = new System.Drawing.Point(160, 43);
            this.lbel_pinlv_day.Name = "lbel_pinlv_day";
            this.lbel_pinlv_day.Size = new System.Drawing.Size(17, 12);
            this.lbel_pinlv_day.TabIndex = 22;
            this.lbel_pinlv_day.Text = "天";
            // 
            // lbel_pinlvSpace
            // 
            this.lbel_pinlvSpace.AutoSize = true;
            this.lbel_pinlvSpace.Location = new System.Drawing.Point(22, 41);
            this.lbel_pinlvSpace.Name = "lbel_pinlvSpace";
            this.lbel_pinlvSpace.Size = new System.Drawing.Size(65, 12);
            this.lbel_pinlvSpace.TabIndex = 20;
            this.lbel_pinlvSpace.Text = "执行间隔：";
            // 
            // drpd_pinlv
            // 
            this.drpd_pinlv.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.drpd_pinlv.FormattingEnabled = true;
            this.drpd_pinlv.Location = new System.Drawing.Point(93, 14);
            this.drpd_pinlv.Name = "drpd_pinlv";
            this.drpd_pinlv.Size = new System.Drawing.Size(127, 20);
            this.drpd_pinlv.TabIndex = 19;
            this.drpd_pinlv.SelectedIndexChanged += new System.EventHandler(this.drpd_pinlv_SelectedIndexChanged);
            // 
            // lbel_actType
            // 
            this.lbel_actType.AutoSize = true;
            this.lbel_actType.Location = new System.Drawing.Point(46, 17);
            this.lbel_actType.Name = "lbel_actType";
            this.lbel_actType.Size = new System.Drawing.Size(41, 12);
            this.lbel_actType.TabIndex = 4;
            this.lbel_actType.Text = "执行：";
            // 
            // gbox_everydaymore
            // 
            this.gbox_everydaymore.Controls.Add(this.dtme_MoreTime);
            this.gbox_everydaymore.Controls.Add(this.drpd_mtpinlv_time);
            this.gbox_everydaymore.Controls.Add(this.drpd_timeType);
            this.gbox_everydaymore.Controls.Add(this.rbtn_mtpinlv_more);
            this.gbox_everydaymore.Controls.Add(this.rbtn_mtpinlv_once);
            this.gbox_everydaymore.Location = new System.Drawing.Point(43, 381);
            this.gbox_everydaymore.Name = "gbox_everydaymore";
            this.gbox_everydaymore.Size = new System.Drawing.Size(634, 82);
            this.gbox_everydaymore.TabIndex = 24;
            this.gbox_everydaymore.TabStop = false;
            this.gbox_everydaymore.Text = "每天频率";
            // 
            // dtme_MoreTime
            // 
            this.dtme_MoreTime.Format = System.Windows.Forms.DateTimePickerFormat.Time;
            this.dtme_MoreTime.Location = new System.Drawing.Point(143, 16);
            this.dtme_MoreTime.Name = "dtme_MoreTime";
            this.dtme_MoreTime.ShowUpDown = true;
            this.dtme_MoreTime.Size = new System.Drawing.Size(98, 21);
            this.dtme_MoreTime.TabIndex = 4;
            // 
            // drpd_mtpinlv_time
            // 
            this.drpd_mtpinlv_time.Enabled = false;
            this.drpd_mtpinlv_time.Location = new System.Drawing.Point(93, 52);
            this.drpd_mtpinlv_time.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.drpd_mtpinlv_time.Name = "drpd_mtpinlv_time";
            this.drpd_mtpinlv_time.Size = new System.Drawing.Size(127, 21);
            this.drpd_mtpinlv_time.TabIndex = 24;
            this.drpd_mtpinlv_time.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // drpd_timeType
            // 
            this.drpd_timeType.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.drpd_timeType.Enabled = false;
            this.drpd_timeType.FormattingEnabled = true;
            this.drpd_timeType.Location = new System.Drawing.Point(244, 53);
            this.drpd_timeType.Name = "drpd_timeType";
            this.drpd_timeType.Size = new System.Drawing.Size(127, 20);
            this.drpd_timeType.TabIndex = 25;
            this.drpd_timeType.SelectedIndexChanged += new System.EventHandler(this.drpd_timeType_SelectedIndexChanged);
            // 
            // rbtn_mtpinlv_more
            // 
            this.rbtn_mtpinlv_more.AutoSize = true;
            this.rbtn_mtpinlv_more.Location = new System.Drawing.Point(6, 53);
            this.rbtn_mtpinlv_more.Name = "rbtn_mtpinlv_more";
            this.rbtn_mtpinlv_more.Size = new System.Drawing.Size(83, 16);
            this.rbtn_mtpinlv_more.TabIndex = 1;
            this.rbtn_mtpinlv_more.Text = "执行间隔：";
            this.rbtn_mtpinlv_more.UseVisualStyleBackColor = true;
            this.rbtn_mtpinlv_more.CheckedChanged += new System.EventHandler(this.rbtn_mtpinlv_more_CheckedChanged);
            // 
            // rbtn_mtpinlv_once
            // 
            this.rbtn_mtpinlv_once.AutoSize = true;
            this.rbtn_mtpinlv_once.Checked = true;
            this.rbtn_mtpinlv_once.Location = new System.Drawing.Point(6, 20);
            this.rbtn_mtpinlv_once.Name = "rbtn_mtpinlv_once";
            this.rbtn_mtpinlv_once.Size = new System.Drawing.Size(131, 16);
            this.rbtn_mtpinlv_once.TabIndex = 0;
            this.rbtn_mtpinlv_once.TabStop = true;
            this.rbtn_mtpinlv_once.Text = "执行一次，时间为：";
            this.rbtn_mtpinlv_once.UseVisualStyleBackColor = true;
            this.rbtn_mtpinlv_once.CheckedChanged += new System.EventHandler(this.rbtn_mtpinlv_once_CheckedChanged);
            // 
            // gbox_moreCTime
            // 
            this.gbox_moreCTime.Controls.Add(this.dtme_lastEndTime);
            this.gbox_moreCTime.Controls.Add(this.dtme_lastStartTime);
            this.gbox_moreCTime.Controls.Add(this.dtme_cxTime_endTime);
            this.gbox_moreCTime.Controls.Add(this.rbtn_cxTime_noendTime);
            this.gbox_moreCTime.Controls.Add(this.rbtn_cxTime_endTime);
            this.gbox_moreCTime.Controls.Add(this.dtme_cxTime_startTime);
            this.gbox_moreCTime.Controls.Add(this.txtb_morestartTime);
            this.gbox_moreCTime.Location = new System.Drawing.Point(43, 480);
            this.gbox_moreCTime.Name = "gbox_moreCTime";
            this.gbox_moreCTime.Size = new System.Drawing.Size(635, 82);
            this.gbox_moreCTime.TabIndex = 26;
            this.gbox_moreCTime.TabStop = false;
            this.gbox_moreCTime.Text = "持续时间";
            // 
            // dtme_cxTime_endTime
            // 
            this.dtme_cxTime_endTime.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dtme_cxTime_endTime.Location = new System.Drawing.Point(368, 22);
            this.dtme_cxTime_endTime.Name = "dtme_cxTime_endTime";
            this.dtme_cxTime_endTime.Size = new System.Drawing.Size(101, 21);
            this.dtme_cxTime_endTime.TabIndex = 7;
            // 
            // rbtn_cxTime_noendTime
            // 
            this.rbtn_cxTime_noendTime.AutoSize = true;
            this.rbtn_cxTime_noendTime.Location = new System.Drawing.Point(279, 60);
            this.rbtn_cxTime_noendTime.Name = "rbtn_cxTime_noendTime";
            this.rbtn_cxTime_noendTime.Size = new System.Drawing.Size(95, 16);
            this.rbtn_cxTime_noendTime.TabIndex = 6;
            this.rbtn_cxTime_noendTime.Text = "无结束日期：";
            this.rbtn_cxTime_noendTime.UseVisualStyleBackColor = true;
            this.rbtn_cxTime_noendTime.CheckedChanged += new System.EventHandler(this.rbtn_cxTime_noendTime_CheckedChanged);
            // 
            // rbtn_cxTime_endTime
            // 
            this.rbtn_cxTime_endTime.AutoSize = true;
            this.rbtn_cxTime_endTime.Checked = true;
            this.rbtn_cxTime_endTime.Location = new System.Drawing.Point(279, 26);
            this.rbtn_cxTime_endTime.Name = "rbtn_cxTime_endTime";
            this.rbtn_cxTime_endTime.Size = new System.Drawing.Size(83, 16);
            this.rbtn_cxTime_endTime.TabIndex = 5;
            this.rbtn_cxTime_endTime.TabStop = true;
            this.rbtn_cxTime_endTime.Text = "结束日期：";
            this.rbtn_cxTime_endTime.UseVisualStyleBackColor = true;
            this.rbtn_cxTime_endTime.CheckedChanged += new System.EventHandler(this.rbtn_cxshijian_endtime_CheckedChanged);
            // 
            // dtme_cxTime_startTime
            // 
            this.dtme_cxTime_startTime.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dtme_cxTime_startTime.Location = new System.Drawing.Point(69, 20);
            this.dtme_cxTime_startTime.Name = "dtme_cxTime_startTime";
            this.dtme_cxTime_startTime.Size = new System.Drawing.Size(98, 21);
            this.dtme_cxTime_startTime.TabIndex = 3;
            // 
            // txtb_morestartTime
            // 
            this.txtb_morestartTime.AutoSize = true;
            this.txtb_morestartTime.Location = new System.Drawing.Point(6, 26);
            this.txtb_morestartTime.Name = "txtb_morestartTime";
            this.txtb_morestartTime.Size = new System.Drawing.Size(65, 12);
            this.txtb_morestartTime.TabIndex = 2;
            this.txtb_morestartTime.Text = "开始日期：";
            // 
            // lbel_content
            // 
            this.lbel_content.AutoSize = true;
            this.lbel_content.Location = new System.Drawing.Point(271, 78);
            this.lbel_content.Name = "lbel_content";
            this.lbel_content.Size = new System.Drawing.Size(65, 12);
            this.lbel_content.TabIndex = 28;
            this.lbel_content.Text = "检验内容：";
            // 
            // txtb_aim
            // 
            this.txtb_aim.Location = new System.Drawing.Point(342, 74);
            this.txtb_aim.Name = "txtb_aim";
            this.txtb_aim.Size = new System.Drawing.Size(63, 21);
            this.txtb_aim.TabIndex = 29;
            // 
            // lbel_hang
            // 
            this.lbel_hang.AutoSize = true;
            this.lbel_hang.Location = new System.Drawing.Point(590, 46);
            this.lbel_hang.Name = "lbel_hang";
            this.lbel_hang.Size = new System.Drawing.Size(53, 12);
            this.lbel_hang.TabIndex = 30;
            this.lbel_hang.Text = "行(数字)";
            // 
            // txtb_lei
            // 
            this.txtb_lei.Location = new System.Drawing.Point(646, 40);
            this.txtb_lei.Name = "txtb_lei";
            this.txtb_lei.Size = new System.Drawing.Size(33, 21);
            this.txtb_lei.TabIndex = 31;
            // 
            // lbel_lei
            // 
            this.lbel_lei.AutoSize = true;
            this.lbel_lei.Location = new System.Drawing.Point(694, 45);
            this.lbel_lei.Name = "lbel_lei";
            this.lbel_lei.Size = new System.Drawing.Size(53, 12);
            this.lbel_lei.TabIndex = 32;
            this.lbel_lei.Text = "列(字母)";
            // 
            // txtb_email
            // 
            this.txtb_email.Location = new System.Drawing.Point(136, 107);
            this.txtb_email.Multiline = true;
            this.txtb_email.Name = "txtb_email";
            this.txtb_email.Size = new System.Drawing.Size(206, 62);
            this.txtb_email.TabIndex = 17;
          
            // 
            // lbel_article
            // 
            this.lbel_article.AutoSize = true;
            this.lbel_article.Location = new System.Drawing.Point(397, 107);
            this.lbel_article.Name = "lbel_article";
            this.lbel_article.Size = new System.Drawing.Size(65, 12);
            this.lbel_article.TabIndex = 33;
            this.lbel_article.Text = "邮件正文：";
            // 
            // txtb_article
            // 
            this.txtb_article.Location = new System.Drawing.Point(472, 104);
            this.txtb_article.Multiline = true;
            this.txtb_article.Name = "txtb_article";
            this.txtb_article.Size = new System.Drawing.Size(206, 62);
            this.txtb_article.TabIndex = 34;
            // 
            // lbel_attachment
            // 
            this.lbel_attachment.AutoSize = true;
            this.lbel_attachment.Location = new System.Drawing.Point(423, 77);
            this.lbel_attachment.Name = "lbel_attachment";
            this.lbel_attachment.Size = new System.Drawing.Size(89, 12);
            this.lbel_attachment.TabIndex = 35;
            this.lbel_attachment.Text = "邮件附件地址：";
            // 
            // txtb_attachmentSource
            // 
            this.txtb_attachmentSource.Location = new System.Drawing.Point(511, 74);
            this.txtb_attachmentSource.Name = "txtb_attachmentSource";
            this.txtb_attachmentSource.ReadOnly = true;
            this.txtb_attachmentSource.Size = new System.Drawing.Size(106, 21);
            this.txtb_attachmentSource.TabIndex = 36;
            // 
            // bton_searchAttachment
            // 
            this.bton_searchAttachment.Location = new System.Drawing.Point(623, 72);
            this.bton_searchAttachment.Name = "bton_searchAttachment";
            this.bton_searchAttachment.Size = new System.Drawing.Size(55, 23);
            this.bton_searchAttachment.TabIndex = 37;
            this.bton_searchAttachment.Text = "浏览";
            this.bton_searchAttachment.UseVisualStyleBackColor = true;
            this.bton_searchAttachment.Click += new System.EventHandler(this.bton_searchAttachment_Click);
            // 
            // lbel_tableName
            // 
            this.lbel_tableName.AutoSize = true;
            this.lbel_tableName.Location = new System.Drawing.Point(331, 45);
            this.lbel_tableName.Name = "lbel_tableName";
            this.lbel_tableName.Size = new System.Drawing.Size(41, 12);
            this.lbel_tableName.TabIndex = 38;
            this.lbel_tableName.Text = "表名：";
            // 
            // drpd_tableName
            // 
            this.drpd_tableName.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.drpd_tableName.FormattingEnabled = true;
            this.drpd_tableName.Location = new System.Drawing.Point(366, 42);
            this.drpd_tableName.Name = "drpd_tableName";
            this.drpd_tableName.Size = new System.Drawing.Size(110, 20);
            this.drpd_tableName.TabIndex = 39;
            // 
            // lbel_fileBName
            // 
            this.lbel_fileBName.AutoSize = true;
            this.lbel_fileBName.Location = new System.Drawing.Point(53, 12);
            this.lbel_fileBName.Name = "lbel_fileBName";
            this.lbel_fileBName.Size = new System.Drawing.Size(77, 12);
            this.lbel_fileBName.TabIndex = 40;
            this.lbel_fileBName.Text = "选择文件夹：";
            // 
            // bton_filesearch
            // 
            this.bton_filesearch.Location = new System.Drawing.Point(273, 9);
            this.bton_filesearch.Name = "bton_filesearch";
            this.bton_filesearch.Size = new System.Drawing.Size(55, 23);
            this.bton_filesearch.TabIndex = 41;
            this.bton_filesearch.Text = "浏览";
            this.bton_filesearch.UseVisualStyleBackColor = true;
            this.bton_filesearch.Click += new System.EventHandler(this.button1_Click);
            // 
            // txtb_fileBName
            // 
            this.txtb_fileBName.Location = new System.Drawing.Point(136, 9);
            this.txtb_fileBName.Name = "txtb_fileBName";
            this.txtb_fileBName.ReadOnly = true;
            this.txtb_fileBName.Size = new System.Drawing.Size(127, 21);
            this.txtb_fileBName.TabIndex = 42;
            // 
            // bton_fileClear
            // 
            this.bton_fileClear.Location = new System.Drawing.Point(276, 41);
            this.bton_fileClear.Name = "bton_fileClear";
            this.bton_fileClear.Size = new System.Drawing.Size(52, 23);
            this.bton_fileClear.TabIndex = 43;
            this.bton_fileClear.Text = "清空";
            this.bton_fileClear.UseVisualStyleBackColor = true;
            this.bton_fileClear.Click += new System.EventHandler(this.bton_fileClear_Click);
            // 
            // bton_fileBName
            // 
            this.bton_fileBName.Location = new System.Drawing.Point(341, 9);
            this.bton_fileBName.Name = "bton_fileBName";
            this.bton_fileBName.Size = new System.Drawing.Size(52, 23);
            this.bton_fileBName.TabIndex = 44;
            this.bton_fileBName.Text = "清空";
            this.bton_fileBName.UseVisualStyleBackColor = true;
            this.bton_fileBName.Click += new System.EventHandler(this.bton_fileBName_Click);
            // 
            // dtme_lastStartTime
            // 
            this.dtme_lastStartTime.Format = System.Windows.Forms.DateTimePickerFormat.Time;
            this.dtme_lastStartTime.Location = new System.Drawing.Point(175, 20);
            this.dtme_lastStartTime.Name = "dtme_lastStartTime";
            this.dtme_lastStartTime.ShowUpDown = true;
            this.dtme_lastStartTime.Size = new System.Drawing.Size(98, 21);
            this.dtme_lastStartTime.TabIndex = 4;
            // 
            // dtme_lastEndTime
            // 
            this.dtme_lastEndTime.Format = System.Windows.Forms.DateTimePickerFormat.Time;
            this.dtme_lastEndTime.Location = new System.Drawing.Point(476, 22);
            this.dtme_lastEndTime.Name = "dtme_lastEndTime";
            this.dtme_lastEndTime.ShowUpDown = true;
            this.dtme_lastEndTime.Size = new System.Drawing.Size(98, 21);
            this.dtme_lastEndTime.TabIndex = 8;
            // 
            // FormAddReportforms
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(748, 612);
            this.Controls.Add(this.bton_fileBName);
            this.Controls.Add(this.bton_fileClear);
            this.Controls.Add(this.txtb_fileBName);
            this.Controls.Add(this.bton_filesearch);
            this.Controls.Add(this.lbel_fileBName);
            this.Controls.Add(this.drpd_tableName);
            this.Controls.Add(this.lbel_tableName);
            this.Controls.Add(this.bton_searchAttachment);
            this.Controls.Add(this.txtb_attachmentSource);
            this.Controls.Add(this.lbel_attachment);
            this.Controls.Add(this.txtb_article);
            this.Controls.Add(this.lbel_article);
            this.Controls.Add(this.lbel_lei);
            this.Controls.Add(this.txtb_lei);
            this.Controls.Add(this.lbel_hang);
            this.Controls.Add(this.txtb_aim);
            this.Controls.Add(this.lbel_content);
            this.Controls.Add(this.gbox_moreCTime);
            this.Controls.Add(this.gbox_everydaymore);
            this.Controls.Add(this.gbox_more);
            this.Controls.Add(this.txtb_email);
            this.Controls.Add(this.gbox_once);
            this.Controls.Add(this.drpd_reportType);
            this.Controls.Add(this.lbel_planType);
            this.Controls.Add(this.drpd_symbol);
            this.Controls.Add(this.bton_back);
            this.Controls.Add(this.bton_search);
            this.Controls.Add(this.txtb_reportSource);
            this.Controls.Add(this.txtb_hang);
            this.Controls.Add(this.lbel_email);
            this.Controls.Add(this.lbel_symbol);
            this.Controls.Add(this.lbel_position);
            this.Controls.Add(this.lbel_reportSource);
            this.Controls.Add(this.bton_sure);
            this.Name = "FormAddReportforms";
            this.Text = "规则设置界面";
            this.Load += new System.EventHandler(this.FormAddReportforms_Load);
            this.gbox_once.ResumeLayout(false);
            this.gbox_once.PerformLayout();
            this.gbox_more.ResumeLayout(false);
            this.gbox_more.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.drpd_pinlv_month)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.drpd_pinlv_day)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.drpd_pinlv_week)).EndInit();
            this.gbox_everydaymore.ResumeLayout(false);
            this.gbox_everydaymore.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.drpd_mtpinlv_time)).EndInit();
            this.gbox_moreCTime.ResumeLayout(false);
            this.gbox_moreCTime.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button bton_sure;
        private System.Windows.Forms.Label lbel_reportSource;
        private System.Windows.Forms.Label lbel_position;
        private System.Windows.Forms.Label lbel_symbol;
        private System.Windows.Forms.Label lbel_email;
        private System.Windows.Forms.TextBox txtb_hang;
        private System.Windows.Forms.TextBox txtb_reportSource;
        private System.Windows.Forms.Button bton_search;
        private System.Windows.Forms.Button bton_back;
        private System.Windows.Forms.ComboBox drpd_symbol;
        private System.Windows.Forms.Label lbel_planType;
        private System.Windows.Forms.ComboBox drpd_reportType;
        private System.Windows.Forms.GroupBox gbox_once;
        private System.Windows.Forms.Label lbel_oncestartDate;
        private System.Windows.Forms.DateTimePicker dtme_OnceDateTime;
        private System.Windows.Forms.DateTimePicker dtme_OnceTime;
        private System.Windows.Forms.Label lbel_oncetime;
        private System.Windows.Forms.GroupBox gbox_more;
        private System.Windows.Forms.ComboBox drpd_pinlv;
        private System.Windows.Forms.Label lbel_actType;
        private System.Windows.Forms.Label lbel_pinlvSpace;
        private System.Windows.Forms.Label lbel_pinlv_day;
        private System.Windows.Forms.NumericUpDown drpd_pinlv_week;
        private System.Windows.Forms.GroupBox gbox_everydaymore;
        private System.Windows.Forms.RadioButton rbtn_mtpinlv_once;
        private System.Windows.Forms.ComboBox drpd_timeType;
        private System.Windows.Forms.RadioButton rbtn_mtpinlv_more;
        private System.Windows.Forms.NumericUpDown drpd_mtpinlv_time;
        private System.Windows.Forms.DateTimePicker dtme_MoreTime;
        private System.Windows.Forms.GroupBox gbox_moreCTime;
        private System.Windows.Forms.DateTimePicker dtme_cxTime_endTime;
        private System.Windows.Forms.RadioButton rbtn_cxTime_noendTime;
        private System.Windows.Forms.RadioButton rbtn_cxTime_endTime;
        private System.Windows.Forms.DateTimePicker dtme_cxTime_startTime;
        private System.Windows.Forms.Label txtb_morestartTime;
        private System.Windows.Forms.Label lbel_pinlv_week;
        private System.Windows.Forms.NumericUpDown drpd_pinlv_day;
        private System.Windows.Forms.CheckBox cbox_pinlv_Saturday;
        private System.Windows.Forms.CheckBox cbox_pinlv_Friday;
        private System.Windows.Forms.CheckBox cbox_pinlv_Thursday;
        private System.Windows.Forms.CheckBox cbox_pinlv_Wednesday;
        private System.Windows.Forms.CheckBox cbox_pinlv_Tuesday;
        private System.Windows.Forms.CheckBox cbox_pinlv_Firstday;
        private System.Windows.Forms.CheckBox cbox_pinlv_Sunday;
        private System.Windows.Forms.Label lbel_content;
        private System.Windows.Forms.TextBox txtb_aim;
        private System.Windows.Forms.Label lbel_hang;
        private System.Windows.Forms.TextBox txtb_lei;
        private System.Windows.Forms.Label lbel_lei;
        private System.Windows.Forms.TextBox txtb_email;
        private System.Windows.Forms.Label lbel_article;
        private System.Windows.Forms.TextBox txtb_article;
        private System.Windows.Forms.Label lbel_attachment;
        private System.Windows.Forms.TextBox txtb_attachmentSource;
        private System.Windows.Forms.Button bton_searchAttachment;
        private System.Windows.Forms.Label lbel_pinlv_month;
        private System.Windows.Forms.NumericUpDown drpd_pinlv_month;
        private System.Windows.Forms.Label lbel_pinlv_mei;
        private System.Windows.Forms.Label lbel_tableName;
        private System.Windows.Forms.ComboBox drpd_tableName;
        private System.Windows.Forms.Label lbel_fileBName;
        private System.Windows.Forms.Button bton_filesearch;
        private System.Windows.Forms.TextBox txtb_fileBName;
        private System.Windows.Forms.FolderBrowserDialog folderBrowserDialog1;
        private System.Windows.Forms.Button bton_fileClear;
        private System.Windows.Forms.Button bton_fileBName;
        private System.Windows.Forms.DateTimePicker dtme_lastEndTime;
        private System.Windows.Forms.DateTimePicker dtme_lastStartTime;

    }
}