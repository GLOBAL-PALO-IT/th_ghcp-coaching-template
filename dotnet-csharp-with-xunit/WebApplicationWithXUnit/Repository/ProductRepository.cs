﻿using Microsoft.EntityFrameworkCore;
using WebApplicationWithXUnit.Data;
using WebApplicationWithXUnit.Interfaces;
using WebApplicationWithXUnit.Models;

namespace WebApplicationWithXUnit.Repository
{
    public class ProductRepository : IProductRepository
    {
        private readonly ApplicationDbContext _context;
        public ProductRepository(ApplicationDbContext context)
        {
            _context = context;
        }
        public async Task<IEnumerable<Product>> GetAll()
        {
            return await _context.Products.ToListAsync();
        }
    }
}
