<script setup lang="ts">

import {reactive, ref} from 'vue';
import axios from "axios";
import TableLite from "vue3-table-lite/ts";



const users = ref();




axios({
  method: 'get',
  url: 'http://localhost:8080/digg/user',
  headers: {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
  },
}).then(response => users.value = response.data);

const table = reactive({
  isLoading: false,
  columns: [
    {
      label: "Name",
      field: "name",
      sortable: true,
    },
    {
      label: "Address",
      field: "address",
      sortable: true,
    },
    {
      label: "Telephone",
      field: "telephone",
      sortable: true,
    },
    {
      label: "Delete",
      field: "delete",
    },
  ],
  rows: users,
  sortable: {
    order: "name",
    sort: "asc",
  },
})
function deleteUser(user: any) {
  axios({
    method: 'delete',
    url: 'http://localhost:8080/digg/user/{{user.id}}',
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
    },
  }).then(response => users.value = response.data);


}

// Get data first
console.log(users)
</script>

<template>
  <div>
    <h5 class="card-header text-center">Users!! I</h5>
      <div v-if="users">
        <h5>Users</h5>
        <table-lite
            :is-loading="table.isLoading"
            :columns="table.columns"
            :rows="table.rows"
            :sortable="table.sortable"
        >

          <template #column-4="props">
            <button type="button" class="btn btn-danger" @click="delete(props.rowData)">Delete</button>
          </template>
        </table-lite>
      </div>
  </div>
</template>