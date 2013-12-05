using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;    

namespace Winform_ExcelWarning
{
    class tools
    {
        public static String connectionString = "Data Source=.;Initial Catalog=ExcelWarning;Integrated Security=True";
   

        public static bool HasEmail(string source)
        {

            return

            Regex.IsMatch(source,

            @"[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})",

            RegexOptions.IgnoreCase);

        }  

    }
}
