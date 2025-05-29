using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebApplicationWithXUnit.Models
{
    public class Product
    {
        [Key]
        public int id { get; set; }

        public required string Name { get; set; }
        public string Description { get; set; }

        [Column(TypeName = "decimal(10,4)")]
        public required decimal Price { get; set; }


    }
}
