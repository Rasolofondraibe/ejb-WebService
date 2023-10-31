using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using sante.Models;



namespace sante.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PersonneController : ControllerBase
    {
        private readonly ILogger<PersonneController> _logger;

        public PersonneController(ILogger<PersonneController> logger)
        {
            _logger = logger;
        }

       
        [HttpGet("getAllPersonne")]
        public List<Personne> getAllPersonne(){
            Personne personne = new Personne ();
            return personne.getAllPersonne();
        }

    }
}