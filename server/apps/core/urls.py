from django.conf.urls.defaults import *

urlpatterns = patterns('core.views',
    url(r'^generate_map/(?P<map_width>.+)/(?P<map_height>.+)/$', 'generate_map', name='generate-map'),
    url(r'^generate_tile_map/(?P<map_width>.+)/(?P<map_height>.+)/$', 'generate_tile_map', name='generate-tile-map'),
)