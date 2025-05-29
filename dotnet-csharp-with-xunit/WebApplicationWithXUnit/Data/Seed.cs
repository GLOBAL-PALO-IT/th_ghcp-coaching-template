using Microsoft.AspNetCore.Identity;
using System.Diagnostics;
using System.Net;
using WebApplicationWithXUnit.Models;

namespace WebApplicationWithXUnit.Data
{
    public class Seed
    {
        public static void SeedData(IApplicationBuilder applicationBuilder)
        {
            using (var serviceScope = applicationBuilder.ApplicationServices.CreateScope())
            {
                var context = serviceScope.ServiceProvider.GetService<ApplicationDbContext>();

                context.Database.EnsureCreated();

                if (!context.Products.Any())
                {
                    context.Products.AddRange(new List<Product>()
                    {
                new Product()
             {
                 Name = "Organic Apple",
                 Description = "A crisp and juicy organic Fuji apple.",
                 Price = 1.29M,
             },
             new Product()
             {
                 Name = "Whole Wheat Bread",
                 Description = "A loaf of freshly baked whole wheat bread.",
                 Price = 3.50M,
             },
             new Product()
             {
                 Name = "Milk (1 Liter)",
                 Description = "Fresh, pasteurized whole milk in a 1-liter carton.",
                 Price = 2.10M,
             },
             new Product()
             {
                 Name = "Free-Range Eggs (Dozen)",
                 Description = "A dozen large, brown, free-range eggs.",
                 Price = 4.75M,
             },
             new Product()
             {
                 Name = "Cheddar Cheese (200g)",
                 Description = "A 200g block of mature cheddar cheese.",
                 Price = 5.99M,
             },
             new Product()
             {
                 Name = "Ground Coffee (250g)",
                 Description = "A 250g bag of medium roast ground Arabica coffee.",
                 Price = 7.25M,
             },
             new Product()
             {
                 Name = "Olive Oil (500ml)",
                 Description = "A 500ml bottle of extra virgin olive oil.",
                 Price = 9.50M,
             },
             new Product()
             {
                 Name = "Pasta (500g)",
                 Description = "A 500g package of Italian spaghetti.",
                 Price = 1.79M,
             },
             new Product()
             {
                 Name = "Chicken Breast (500g)",
                 Description = "500g of boneless, skinless chicken breasts.",
                 Price = 8.99M,
             },
             new Product()
             {
                 Name = "Salmon Fillet (150g)",
                 Description = "A 150g fillet of fresh Atlantic salmon.",
                 Price = 12.50M,
             },
             new Product()
             {
                 Name = "Banana (Bunch)",
                 Description = "A bunch of approximately 5 ripe bananas.",
                 Price = 2.00M,
             },
             new Product()
             {
                 Name = "Tomato (500g)",
                 Description = "500g of ripe, red tomatoes.",
                 Price = 2.75M,
             },
             new Product()
             {
                 Name = "Lettuce (Head)",
                 Description = "A fresh head of Romaine lettuce.",
                 Price = 1.50M,
             },
             new Product()
             {
                 Name = "Onion (1kg)",
                 Description = "A 1kg bag of yellow onions.",
                 Price = 2.20M,
             },
             new Product()
             {
                 Name = "Potato (1kg)",
                 Description = "A 1kg bag of russet potatoes.",
                 Price = 1.80M,
             },
             new Product()
             {
                 Name = "Carrots (1kg)",
                 Description = "A 1kg bag of fresh carrots.",
                 Price = 1.90M,
             },
             new Product()
             {
                 Name = "Broccoli (Head)",
                 Description = "A fresh head of broccoli.",
                 Price = 2.50M,
             },
             new Product()
             {
                 Name = "Bell Pepper (Each)",
                 Description = "A fresh bell pepper (color may vary).",
                 Price = 1.10M,
             },
             new Product()
             {
                 Name = "Cucumber (Each)",
                 Description = "A fresh, long English cucumber.",
                 Price = 1.35M,
             },
             new Product()
             {
                 Name = "Yogurt (500g)",
                 Description = "A 500g container of plain yogurt.",
                 Price = 3.00M,
             },
             new Product()
             {
                 Name = "Orange Juice (1L)",
                 Description = "A 1-liter carton of freshly squeezed orange juice.",
                 Price = 4.25M,
             },
             new Product()
             {
                 Name = "Apple Juice (1L)",
                 Description = "A 1-liter carton of pure apple juice.",
                 Price = 3.75M,
             },
             new Product()
             {
                 Name = "Sparkling Water (1L)",
                 Description = "A 1-liter bottle of natural sparkling water.",
                 Price = 2.80M,
             },
             new Product()
             {
                 Name = "Butter (250g)",
                 Description = "A 250g block of salted butter.",
                 Price = 4.50M,
             },
             new Product()
             {
                 Name = "Jam (300g)",
                 Description = "A 300g jar of strawberry jam.",
                 Price = 3.20M,
             },
             new Product()
             {
                 Name = "Honey (500g)",
                 Description = "A 500g jar of pure natural honey.",
                 Price = 8.00M,
             },
             new Product()
             {
                 Name = "Oatmeal (500g)",
                 Description = "A 500g package of rolled oats.",
                 Price = 2.60M,
             },
             new Product()
             {
                 Name = "Rice (1kg)",
                 Description = "A 1kg bag of long-grain white rice.",
                 Price = 3.15M,
             },
             new Product()
             {
                 Name = "Black Beans (500g)",
                 Description = "A 500g bag of dried black beans.",
                 Price = 2.40M,
             },
             new Product()
             {
                 Name = "Lentils (500g)",
                 Description = "A 500g bag of dried green lentils.",
                 Price = 2.30M,
             },

                    });
                    context.SaveChanges();
                }
               
            }
        }
    }
}
