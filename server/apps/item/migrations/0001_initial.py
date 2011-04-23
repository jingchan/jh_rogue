# encoding: utf-8
import datetime
from south.db import db
from south.v2 import SchemaMigration
from django.db import models

class Migration(SchemaMigration):

    def forwards(self, orm):
        
        # Adding model 'ItemType'
        db.create_table('item_itemtype', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('name', self.gf('django.db.models.fields.TextField')()),
            ('description', self.gf('django.db.models.fields.TextField')()),
            ('ground_asset', self.gf('django.db.models.fields.files.ImageField')(max_length=100)),
            ('inventory_asset', self.gf('django.db.models.fields.files.ImageField')(max_length=100)),
        ))
        db.send_create_signal('item', ['ItemType'])

        # Adding model 'Item'
        db.create_table('item_item', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('name', self.gf('django.db.models.fields.TextField')()),
            ('description', self.gf('django.db.models.fields.TextField')()),
            ('ground_asset', self.gf('django.db.models.fields.files.ImageField')(max_length=100)),
            ('inventory_asset', self.gf('django.db.models.fields.files.ImageField')(max_length=100)),
            ('level', self.gf('django.db.models.fields.IntegerField')(default=1)),
            ('type', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['item.ItemType'])),
        ))
        db.send_create_signal('item', ['Item'])


    def backwards(self, orm):
        
        # Deleting model 'ItemType'
        db.delete_table('item_itemtype')

        # Deleting model 'Item'
        db.delete_table('item_item')


    models = {
        'item.item': {
            'Meta': {'object_name': 'Item'},
            'description': ('django.db.models.fields.TextField', [], {}),
            'ground_asset': ('django.db.models.fields.files.ImageField', [], {'max_length': '100'}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'inventory_asset': ('django.db.models.fields.files.ImageField', [], {'max_length': '100'}),
            'level': ('django.db.models.fields.IntegerField', [], {'default': '1'}),
            'name': ('django.db.models.fields.TextField', [], {}),
            'type': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['item.ItemType']"})
        },
        'item.itemtype': {
            'Meta': {'object_name': 'ItemType'},
            'description': ('django.db.models.fields.TextField', [], {}),
            'ground_asset': ('django.db.models.fields.files.ImageField', [], {'max_length': '100'}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'inventory_asset': ('django.db.models.fields.files.ImageField', [], {'max_length': '100'}),
            'name': ('django.db.models.fields.TextField', [], {})
        }
    }

    complete_apps = ['item']
