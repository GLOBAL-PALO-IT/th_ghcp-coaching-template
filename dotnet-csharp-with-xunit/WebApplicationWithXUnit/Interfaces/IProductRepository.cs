using System.Diagnostics;
using WebApplicationWithXUnit.Models;

namespace WebApplicationWithXUnit.Interfaces
{
    public interface IProductRepository
    {
        Task<IEnumerable<Product>> GetAll();
    }
}
