using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SushiBarManager.Model
{
   
    public class Ingredient
    {
        public int id { get; set; }
        public string name { get; set; }
        public string quantity { get; set; }
    }

    public class Meal
    {
        public int id { get; set; }
        public string name { get; set; }
        public string category { get; set; }
        public object details { get; set; }
        public string image { get; set; }
        public string properTime { get; set; }
        public List<Ingredient> ingredients { get; set; }
        public int price { get; set; }
        public bool possibleToDo { get; set; }
    }

    public class Menu
    {
        public int id { get; set; }
        public string name { get; set; }
        public List<Meal> meals { get; set; }
        public bool current { get; set; }
    }
}
