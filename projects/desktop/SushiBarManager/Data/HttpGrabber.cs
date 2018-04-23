using Newtonsoft.Json;
using SushiBarManager.Model;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace SushiBarManager.Data
{
    
    public class HttpGrabber
    {
        private string BaseAddres = "http://sushi.mimosa-soft.com/";

        /// <summary>
        /// Serialize RootObject
        /// </summary>
        public async Task<List<Ingredient>> SerializeString()
        {
            List<Ingredient> root = new List<Ingredient>();

            try
            {
                root = JsonConvert.DeserializeObject<List<Ingredient>>(await GetFromHttp());
             
            }

            catch(Exception ex)
            {
                var a = ex.Message.ToString();
            }

            return root;
        }

        /// <summary>
        /// Get data from Http
        /// </summary>
        /// <returns></returns>
        public async Task<string> GetFromHttp()
        {

            string TestRequest = "";

            try
            {
                var request = HttpWebRequest.CreateHttp(BaseAddres + "ingredient");
                request.Method = WebRequestMethods.Http.Get;

                request.ContentType = "application/json; charset=utf-8";

                await Task.Factory.FromAsync<WebResponse>(request.BeginGetResponse, request.EndGetResponse, null)
                   .ContinueWith(task =>
                   {
                       var response = (HttpWebResponse)task.Result;

                       if(response.StatusCode == HttpStatusCode.OK)
                       {
                           StreamReader responseReader = new StreamReader(response.GetResponseStream(), Encoding.UTF8);
                           string responseData = responseReader.ReadToEnd();

                           TestRequest = responseData.ToString();
                           responseReader.Close();
                       }

                       response.Close();

                   });
            }

            catch(Exception)
            {

            }

            return TestRequest;
        }
    }
}
