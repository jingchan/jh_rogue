# encoding: utf-8
import datetime
from south.db import db
from south.v2 import SchemaMigration
from django.db import models

class Migration(SchemaMigration):

    def forwards(self, orm):
        
        # Adding model 'GameInstance'
        db.create_table('core_gameinstance', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('mode', self.gf('django.db.models.fields.TextField')()),
            ('max_players', self.gf('django.db.models.fields.IntegerField')()),
        ))
        db.send_create_signal('core', ['GameInstance'])

        # Adding model 'MapInstance'
        db.create_table('core_mapinstance', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('map', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['map.Map'])),
        ))
        db.send_create_signal('core', ['MapInstance'])

        # Adding model 'ConnectedCharacter'
        db.create_table('core_connectedcharacter', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('instance', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['core.GameInstance'])),
            ('map', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['core.MapInstance'])),
            ('location', self.gf('django.db.models.fields.TextField')()),
            ('status', self.gf('django.db.models.fields.TextField')()),
            ('character', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['character.Character'])),
        ))
        db.send_create_signal('core', ['ConnectedCharacter'])

        # Adding model 'MobInstance'
        db.create_table('core_mobinstance', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('map', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['core.MapInstance'])),
            ('location', self.gf('django.db.models.fields.TextField')()),
            ('status', self.gf('django.db.models.fields.TextField')()),
            ('mob', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['mob.Mob'])),
        ))
        db.send_create_signal('core', ['MobInstance'])

        # Adding model 'ItemInstance'
        db.create_table('core_iteminstance', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('instance', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['core.MapInstance'])),
            ('location', self.gf('django.db.models.fields.TextField')()),
            ('item', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['item.Item'])),
        ))
        db.send_create_signal('core', ['ItemInstance'])


    def backwards(self, orm):
        
        # Deleting model 'GameInstance'
        db.delete_table('core_gameinstance')

        # Deleting model 'MapInstance'
        db.delete_table('core_mapinstance')

        # Deleting model 'ConnectedCharacter'
        db.delete_table('core_connectedcharacter')

        # Deleting model 'MobInstance'
        db.delete_table('core_mobinstance')

        # Deleting model 'ItemInstance'
        db.delete_table('core_iteminstance')


    models = {
        'auth.group': {
            'Meta': {'object_name': 'Group'},
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'name': ('django.db.models.fields.CharField', [], {'unique': 'True', 'max_length': '80'}),
            'permissions': ('django.db.models.fields.related.ManyToManyField', [], {'to': "orm['auth.Permission']", 'symmetrical': 'False', 'blank': 'True'})
        },
        'auth.permission': {
            'Meta': {'ordering': "('content_type__app_label', 'content_type__model', 'codename')", 'unique_together': "(('content_type', 'codename'),)", 'object_name': 'Permission'},
            'codename': ('django.db.models.fields.CharField', [], {'max_length': '100'}),
            'content_type': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['contenttypes.ContentType']"}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'name': ('django.db.models.fields.CharField', [], {'max_length': '50'})
        },
        'auth.user': {
            'Meta': {'object_name': 'User'},
            'date_joined': ('django.db.models.fields.DateTimeField', [], {'default': 'datetime.datetime.now'}),
            'email': ('django.db.models.fields.EmailField', [], {'max_length': '75', 'blank': 'True'}),
            'first_name': ('django.db.models.fields.CharField', [], {'max_length': '30', 'blank': 'True'}),
            'groups': ('django.db.models.fields.related.ManyToManyField', [], {'to': "orm['auth.Group']", 'symmetrical': 'False', 'blank': 'True'}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'is_active': ('django.db.models.fields.BooleanField', [], {'default': 'True'}),
            'is_staff': ('django.db.models.fields.BooleanField', [], {'default': 'False'}),
            'is_superuser': ('django.db.models.fields.BooleanField', [], {'default': 'False'}),
            'last_login': ('django.db.models.fields.DateTimeField', [], {'default': 'datetime.datetime.now'}),
            'last_name': ('django.db.models.fields.CharField', [], {'max_length': '30', 'blank': 'True'}),
            'password': ('django.db.models.fields.CharField', [], {'max_length': '128'}),
            'user_permissions': ('django.db.models.fields.related.ManyToManyField', [], {'to': "orm['auth.Permission']", 'symmetrical': 'False', 'blank': 'True'}),
            'username': ('django.db.models.fields.CharField', [], {'unique': 'True', 'max_length': '30'})
        },
        'character.archtype': {
            'Meta': {'object_name': 'Archtype'},
            'description': ('django.db.models.fields.TextField', [], {}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'name': ('django.db.models.fields.TextField', [], {})
        },
        'character.character': {
            'Meta': {'object_name': 'Character'},
            'archtype': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['character.Archtype']"}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'name': ('django.db.models.fields.TextField', [], {}),
            'user': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['auth.User']"})
        },
        'contenttypes.contenttype': {
            'Meta': {'ordering': "('name',)", 'unique_together': "(('app_label', 'model'),)", 'object_name': 'ContentType', 'db_table': "'django_content_type'"},
            'app_label': ('django.db.models.fields.CharField', [], {'max_length': '100'}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'model': ('django.db.models.fields.CharField', [], {'max_length': '100'}),
            'name': ('django.db.models.fields.CharField', [], {'max_length': '100'})
        },
        'core.connectedcharacter': {
            'Meta': {'object_name': 'ConnectedCharacter'},
            'character': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['character.Character']"}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'instance': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['core.GameInstance']"}),
            'location': ('django.db.models.fields.TextField', [], {}),
            'map': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['core.MapInstance']"}),
            'status': ('django.db.models.fields.TextField', [], {})
        },
        'core.gameinstance': {
            'Meta': {'object_name': 'GameInstance'},
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'max_players': ('django.db.models.fields.IntegerField', [], {}),
            'mode': ('django.db.models.fields.TextField', [], {})
        },
        'core.iteminstance': {
            'Meta': {'object_name': 'ItemInstance'},
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'instance': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['core.MapInstance']"}),
            'item': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['item.Item']"}),
            'location': ('django.db.models.fields.TextField', [], {})
        },
        'core.mapinstance': {
            'Meta': {'object_name': 'MapInstance'},
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'map': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['map.Map']"})
        },
        'core.mobinstance': {
            'Meta': {'object_name': 'MobInstance'},
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'location': ('django.db.models.fields.TextField', [], {}),
            'map': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['core.MapInstance']"}),
            'mob': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['mob.Mob']"}),
            'status': ('django.db.models.fields.TextField', [], {})
        },
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
        },
        'map.map': {
            'Meta': {'object_name': 'Map'},
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'tiles': ('django.db.models.fields.TextField', [], {})
        },
        'mob.mob': {
            'Meta': {'object_name': 'Mob'},
            'description': ('django.db.models.fields.TextField', [], {}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'name': ('django.db.models.fields.TextField', [], {}),
            'type': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['mob.MobType']"})
        },
        'mob.mobtype': {
            'Meta': {'object_name': 'MobType'},
            'description': ('django.db.models.fields.TextField', [], {}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'name': ('django.db.models.fields.TextField', [], {})
        }
    }

    complete_apps = ['core']
