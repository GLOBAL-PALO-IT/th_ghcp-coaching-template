using Microsoft.AspNetCore.Mvc;
using WebApplicationWithXUnit.Interfaces;
using WebApplicationWithXUnit.ViewModel;

namespace WebApplicationWithXUnit.Controllers
{
    public class ProductController : Controller
    {
        private readonly IProductRepository _productRepository;
        public ProductController(IProductRepository productRepository)
        {
            _productRepository = productRepository;
        }

        public async Task<IActionResult> Index()
        {
            var products = await _productRepository.GetAll();
            var viewModel = new IndexProductViewModel
            {
                Products = products,
            };

            return View(viewModel);
        }
    }
}
