const app = Vue.

  createApp({

    data() {
      return {
        clients: [],
        firstName: " ",
        lastName: " ",
        email: " ",

        name: '',
        payments: [],
        maxAmount: '',
        interest: '',
      }
    },

    created() {
      this.cargarDatos()

    },

    methods: {
      cargarDatos() {
        axios.get('/rest/clients')
          .then(response => {
            this.clients = response.data._embedded.clients
          })
          .catch(function (error) {
            console.log(error);
          });
      },

      addClient() {
        axios.post('/rest/clients', {
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
        })
          .then(() => this.cargarDatos())
          .catch(function (error) {
            console.log(error);
          });
      },

      newLoanAdmin() {
        axios.post("/api/loans/admin", `name=${this.name}&payments=${this.payments}&maxAmount=${this.maxAmount}&interest=${this.interest}`,
          { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
          .then(response => {
            swal("The loan has been created!", "warning", {
                timer: 4000,
            })
                .then(response => window.location.reload())
        })
        .catch(error => {
            swal("Error!", error.response.data, "error", {
                timer: 6000,
            })
        })
      },


    }
  }).mount('#app')