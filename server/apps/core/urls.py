from django.conf.urls.defaults import *

urlpatterns = patterns('core.views',
    url(r'^generate_map/$', 'generate_map', name='generate-map'),
)