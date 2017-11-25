from django.contrib import admin

# Register your models here.
from carboncalc.models import User_Main


@admin.register(User_Main)
class User_Admin(admin.ModelAdmin):
    ordering = ['-created_on']
    list_display = ['name', 'email', 'car_model', 'car_company']
    search_fields = ('name', 'email')
    list_filter = ('car_company', 'name')

