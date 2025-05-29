using Microsoft.AspNetCore.Identity;
using System.ComponentModel.DataAnnotations;

namespace WebApplicationWithXUnit.Models
{
    public class AppUser : IdentityUser
    {

        public required string Name { get; set; }

        public required string Email { get; set; }

        public required int Age { get; set; }
    }
}
