using WebApplicationWithXUnit.Controllers;
using WebApplicationWithXUnit.Interfaces;
//using FakeItEasy;
using FluentAssertions;
using FakeItEasy;
using Microsoft.AspNetCore.Mvc;
using WebApplicationWithXUnit.Models;
using WebApplicationWithXUnit.ViewModel;
namespace WebApplicationWithXUnit.Tests.Controller
{
    public class ProductControllerTests
    {
        private ProductController _productController;
        private IProductRepository _productRepository;

        public ProductControllerTests()
        {
            _productRepository =  A.Fake<IProductRepository>();

            _productController = new ProductController(_productRepository);
        }

        [Fact]
        public void ProductController_Index_ReturnsSuccess()
        {
            //Arrange - What do i need to bring in?
            var products = A.Fake<IEnumerable<Product>>();
            A.CallTo(() => _productRepository.GetAll()).Returns(products);
            //Act
            var result = _productController.Index();
            //Assert - Object check actions
            result.Should().BeOfType<Task<IActionResult>>();
        }

        [Fact]
        public async Task ProductController_Index_ReturnsViewResult_WithProducts()
        {
            // Arrange
            var products = new List<Product>
            {
                new Product { id = 1, Name = "Test1", Description = "Desc1", Price = 10.0m },
                new Product { id = 2, Name = "Test2", Description = "Desc2", Price = 20.0m }
            };
            A.CallTo(() => _productRepository.GetAll()).Returns(Task.FromResult<IEnumerable<Product>>(products));

            // Act
            var result = await _productController.Index();

            // Assert
            var viewResult = result.Should().BeOfType<ViewResult>().Subject;

            // Check the model is of the correct ViewModel type
            viewResult.Model.Should().BeAssignableTo<IndexProductViewModel>();
            var model = (IndexProductViewModel)viewResult.Model;

            // Check the Products list inside the ViewModel
            model.Products.Should().HaveCount(2);
        }

        [Fact]
        public async Task ProductController_Index_ReturnsViewResult_WithEmptyList()
        {
            // Arrange
            var products = new List<Product>();
            A.CallTo(() => _productRepository.GetAll()).Returns(Task.FromResult<IEnumerable<Product>>(products));

            // Act
            var result = await _productController.Index();

            // Assert
            var viewResult = result.Should().BeOfType<ViewResult>().Subject;

            // Check the model is of the correct ViewModel type
            viewResult.Model.Should().BeAssignableTo<IndexProductViewModel>();
            var model = (IndexProductViewModel)viewResult.Model;

            // Check the Products list inside the ViewModel
            model.Products.Should().BeEmpty();
        }

        [Fact]
        public async Task ProductController_Index_HandlesRepositoryException()
        {
            // Arrange
            A.CallTo(() => _productRepository.GetAll()).Throws<Exception>();

            // Act
            Func<Task> act = async () => await _productController.Index();

            // Assert
            await act.Should().ThrowAsync<Exception>();
        }

    }
}
