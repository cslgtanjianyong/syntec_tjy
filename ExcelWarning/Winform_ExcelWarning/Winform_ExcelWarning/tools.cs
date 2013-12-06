using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;    
using System.Data.OleDb;
using System.Data;

namespace Winform_ExcelWarning
{
    class tools
    {
        public static String connectionString = "Data Source=.;Initial Catalog=ExcelWarning;Integrated Security=True";

        public static DataTable GetExcelTableName(string p_ExcelFile)
        {
            try
            {
                if (System.IO.File.Exists(p_ExcelFile))
                {
                    OleDbConnection _ExcelConn = new OleDbConnection("Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + p_ExcelFile + ";Extended Properties='Excel 8.0;HDR=Yes;IMEX=1'");
                    _ExcelConn.Open();
                    DataTable _Table = _ExcelConn.GetOleDbSchemaTable(OleDbSchemaGuid.Tables, null);
                    _ExcelConn.Close();
                    return _Table;
                }
                return null;
            }
            catch
            {
                return null;
            }
        }

        public static bool HasEmail(string source)
        {

            return

            Regex.IsMatch(source,

            @"[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})",

            RegexOptions.IgnoreCase);

        }
        public void getNumber()
        {
            Console.WriteLine("请输入要提取的字符串：");
            string str = "10分钟";
            int number = 0; string num = null;
            foreach (char item in str)
            {
                if (item >= 48 && item <= 58)
                {
                    num += item;
                }
            }
            number = int.Parse(num);
            Console.WriteLine(number);
        }
        public static bool validateNum(string strNum)
        {
            return Regex.IsMatch(strNum, "^[0-9]*$");
        }
        public static bool validateString(string str)
        {
            return Regex.IsMatch(str, "^[A-Za-z]*$");
        }
        //第一个参数d1的年月日和d2的时分秒结合
        public static DateTime AddTwoDate(DateTime d1, DateTime d2)
        {
           DateTime t = new DateTime();
           t= t.AddYears(d1.Year - 1);
           t= t.AddMonths(d1.Month - 1);
           t= t.AddDays(d1.Day - 1);
           t= t.AddHours(d2.Hour);
           t= t.AddMinutes(d2.Minute);
           t= t.AddSeconds(d2.Second);
           return t;
        }
        //第一个参数d1的年月日和t1的时分秒结合
        public static DateTime AddTwoDate(DateTime d1, TimeSpan d2)
        {
            DateTime t = new DateTime();
            t = t.AddYears(d1.Year - 1);
            t = t.AddMonths(d1.Month - 1);
            t = t.AddDays(d1.Day - 1);
            t = t.AddHours(d2.Hours);
            t = t.AddMinutes(d2.Minutes);
            t = t.AddSeconds(d2.Seconds);
            return t;
        }

        //最重要的方法，把model传入，传出下一次执行的时间，如果为NULL则规则已经失效
        public static Object getnextToDateTime(syntec.Model.ExcelRule rule) 
        {
            DateTime nextToDateTime = new DateTime();
            DateTime startTime = new DateTime();
            //现在的系统时间大于规则制定开始时间，从现在的系统时间开始计时
            if (DateTime.Now >= rule.startDate)
            {
                startTime = DateTime.Now;
            }
            else
            {
                //如果未来组合后的时间大于截止时间则不执行
                if (AddTwoDate(rule.startDate, rule.startTime) > rule.endDate)
                {
                    return null;
                }
                else 
                {
                    return AddTwoDate(rule.startDate, rule.startTime);
                }
            }
            if (rule.timeType == "D") 
            {
                    //指的是一天只执行一次！！！(*^__^*) 嘻嘻……简单。
                //现在时间或者开始时间大于结束时间，规则不执行
                    if (startTime > rule.endDate)
                    {
                         return null;
                    }
                //现在时间大于开始时间
                    if (startTime > rule.startDate) 
                    {
                        return AddTwoDate(startTime, rule.startTime);
                    }
                    if (rule.everydayFreSpace == null) 
                    {                     
                        startTime.AddDays(Convert.ToInt16(rule.everydayFreSpace));
                       
                    }
                    //指的是一天执行多次！！！烦的来了。
                    else
                    {
                        int time = GetNumberInt(rule.everydayFreSpace);
                        //单位为分钟，MIN
                        if (rule.everydayFreSpace.Substring(rule.everydayFreSpace.Length - 1, 1) == "n")
                        {
                           startTime=startTime.AddMinutes(time);
                        }
                        //单位为小时，HOU
                        else 
                        {
                            startTime.AddHours(time);
                        }
                        
                    }
                   
                   
            }
            if (rule.timeType == "W") 
            {

            }
            if (rule.timeType == "M") 
            {

            }
            return nextToDateTime;
        }
        public static int GetNumberInt(string str)
        {
            int result = 0;
            if (str != null && str != string.Empty)
            {
                // 正则表达式剔除非数字字符（不包含小数点.）

                str = Regex.Replace(str, @"[^\d.\d]", "");

                // 如果是数字，则转换为decimal类型

                if (Regex.IsMatch(str, @"^[+-]?\d*[.]?\d*$"))
                {

                    result = int.Parse(str);

                }

            }

            return result;

        }




    }
}
