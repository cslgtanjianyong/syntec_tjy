using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace Winform_ExcelWarning
{
    class ThreadExcel
    {
        public delegate void delAddFriend(string friInfo);
        FormMain _ff;
        public ThreadExcel(FormMain f)
        {
            _ff = f;
        }
        public void start()
        {
            while (true)
            {
                string describe = "";
                DateTime startTime = DateTime.Now;
                object[] pars = new object[1];
                syntec.BLL.ExcelRule rulebll = new syntec.BLL.ExcelRule();
                List<syntec.Model.ExcelRule> rulelist = new List<syntec.Model.ExcelRule>();
                DateTime d = DateTime.Now.AddMinutes(-5);
                rulelist = rulebll.GetModelList("nextDoTimeDate>'" + d.ToString() + "' and nextDoTimeDate<'" + DateTime.Now + "'");
                //list表中有符合的数据 需要做检测了
                if (rulelist.Count != 0)
                {
                    foreach (syntec.Model.ExcelRule rule in rulelist)
                    {
                        //检测成功，要发邮件了亲
                        if (tools.ExcelIsOK(rule))
                        {
                            //发送邮件
                            describe += "已经发送邮件给" + rule.email;

                            rule.nextDoTimeDate = Convert.ToDateTime(tools.getnextToDateTime(rule, false));
                            
                            rulebll.Update(rule);

                            //填写日志
                            syntec.BLL.Log logbll = new syntec.BLL.Log();
                            syntec.Model.Log log = new syntec.Model.Log();
                            log.RuleID = rule.ID;
                            log.logStartTime = DateTime.Now;
                            log.logDescribe = describe;
                            logbll.Add(log);

                        }
                    }
                }
                //pars[0] = rulelist.Count.ToString();
                //this._ff.BeginInvoke(new delAddFriend(this._ff.addFriend), pars);
                Thread.Sleep(10 * 1000);
                DateTime endTime = DateTime.Now;
                TimeSpan timespan = endTime.Subtract(startTime);

                if (timespan.Seconds <= 60)
                {
                    Thread.Sleep(20 * 1000 - timespan.Seconds * 1000);
                }
            }
        }
    }
}
